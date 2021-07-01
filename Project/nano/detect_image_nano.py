# USAGE
# python detect_image_nano.py --trt-graph model/weapons_model_trt.pb \
# 	--labels model/pascal_label_map.pbtxt --image sample_data/images/armas_103.jpg \
#	--num-classes 1

# import the necessary packages
from object_detection.utils import label_map_util
from tensorflow.python.util import deprecation
import tensorflow.contrib.tensorrt as trt
import tensorflow as tf
import numpy as np
import argparse
import imutils
import time
import cv2
import os

import csv
#string[] name = new string[11]
name=[]

def loadTRTGraph(graphFile):
	# open the graph file
	with tf.gfile.GFile(graphFile, "rb") as f:
		# instantiate the GraphDef class and read the graph
		graphDef = tf.GraphDef()
		graphDef.ParseFromString(f.read())

	# return the graph    
	return graphDef

# turn off the deprecation warnings and logs to 
# keep the console clean for convenience
deprecation._PRINT_DEPRECATION_WARNINGS = False
os.environ["TF_CPP_MIN_LOG_LEVEL"] = "3"

# construct the argument parser and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-t", "--trt-graph", required=True,
	help="path to the input TRT Graph of the weapon detector")
ap.add_argument("-l", "--labels", required=True,
	help="path to the input labels file")
ap.add_argument("-i", "--image", required=True,
	help="path to input image")
ap.add_argument("-n", "--num-classes", type=int, required=True,
	help="# of class labels")
ap.add_argument("-c", "--confidence", type=float, default=0.5,
	help="minimum probability used to filter weak detections")
args = vars(ap.parse_args())

# initialize a set of colors for our class labels
COLORS = np.random.uniform(0, 255, size=(args["num_classes"], 3))

# load the TRT graph
print("[INFO] loading TRT graph...")
trtGraph = loadTRTGraph(args["trt_graph"])

# instantiate the ConfigProto class, enable GPU usage growth, create
# TensorFlow session, and import the TRT graph into the session
print("[INFO] initializing TensorFlow session...")
tfConfig = tf.ConfigProto()
tfConfig.gpu_options.allow_growth = True
tfSess = tf.Session(config=tfConfig)
tf.import_graph_def(trtGraph, name="")

# load the class labels from disk
labelMap = label_map_util.load_labelmap(args["labels"])
categories = label_map_util.convert_label_map_to_categories(
	labelMap, max_num_classes=args["num_classes"],
	use_display_name=True)
categoryIdx = label_map_util.create_category_index(categories)

# grab a reference to both the image and boxes tensors
imageTensor = tfSess.graph.get_tensor_by_name("image_tensor:0")
boxesTensor = tfSess.graph.get_tensor_by_name("detection_boxes:0")

# for each bounding box we would like to know the score
# (i.e., probability) and class label
scoresTensor = tfSess.graph.get_tensor_by_name("detection_scores:0")
classesTensor = tfSess.graph.get_tensor_by_name("detection_classes:0")
numDetections = tfSess.graph.get_tensor_by_name("num_detections:0")

# load the image from disk and resize it to have a maximum width of
# 500 pixels
image = cv2.imread(args["image"])
image = imutils.resize(image, width=500)

# prepare the image for detection
(H, W) = image.shape[:2]
output = image.copy()
image = cv2.cvtColor(image.copy(), cv2.COLOR_BGR2RGB)
image = np.expand_dims(image, axis=0)

# perform inference and compute the bounding boxes,
# probabilities, and class labels
start = time.time()
(boxes, scores, labels, N) = tfSess.run(
	[boxesTensor, scoresTensor, classesTensor, numDetections],
	feed_dict={imageTensor: image})
end = time.time()
print("[INFO] object detection took {:.4f} seconds...".format(
	end - start))

# squeeze the lists into a single dimension
boxes = np.squeeze(boxes)
scores = np.squeeze(scores)
labels = np.squeeze(labels)

# loop over the bounding box predictions
for (box, score, label) in zip(boxes, scores, labels):
	# if the predicted probability is less than the minimum
	# confidence, ignore it
	if score < args["confidence"]:
		continue

	# scale the bounding box from the range [0, 1] to [W, H]
	(startY, startX, endY, endX) = box
	startX = int(startX * W)
	startY = int(startY * H)
	endX = int(endX * W)
	endY = int(endY * H)

	# draw the prediction on the output image
	label = categoryIdx[label]
	idx = int(label["id"]) - 1
	label = "{}: {:.2f}".format(label["name"], score)
	cv2.rectangle(output, (startX, startY), (endX, endY),
		COLORS[idx], 2)
	y = startY - 10 if startY - 10 > 10 else startY + 10
	cv2.putText(output, label, (startX, y),
		cv2.FONT_HERSHEY_SIMPLEX, 0.3, COLORS[idx], 1)
	

	#print the label
	print(label)

	#array
	name.append(label)

# 開啟輸出的 CSV 檔案
with open('output.csv', 'w', newline='') as csvfile:
	# 建立 CSV 檔寫入器
	writer = csv.writer(csvfile)
	writer.writerow(name)


# show the output image
cv2.imshow("Output", output)
cv2.waitKey(0)
