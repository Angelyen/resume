# USAGE
# python detect_realtime_nano.py \
#   --trt-graph model/pills_model_trt.pb \
#   --labels model/pascal_label_map.pbtxt \
#   --num-classes 56

# import the necessary packages
from object_detection.utils import visualization_utils as vis_util
from object_detection.utils import label_map_util
from tensorflow.python.util import deprecation
from imutils.video import VideoStream
from imutils.video import FPS
import tensorflow.contrib.tensorrt as trt
import tensorflow as tf
import numpy as np
import argparse
import imutils
import time
import cv2
import os
import csv
import paramiko


name=[]
i=0

def loadTRTGraph(graphFile):
	# open the graph file
	with tf.gfile.GFile(graphFile, "rb") as f:
		# instantiate the GraphDef class and read the graph
		graphDef = tf.GraphDef()
		graphDef.ParseFromString(f.read())

	# return the graph    
	return graphDef

# turn off the deprecation warnings and logs to keep 
# the console clean for convenience
deprecation._PRINT_DEPRECATION_WARNINGS = False
os.environ["TF_CPP_MIN_LOG_LEVEL"] = "3"

# construct the argument parser and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-t", "--trt-graph", required=True,
	help="path to the input TRT Graph of the weapon detector")
ap.add_argument("-l", "--labels", required=True,
	help="path to the input labels file")
ap.add_argument("-n", "--num-classes", type=int, required=True,
	help="# of class labels")
ap.add_argument("-i", "--input",
	help="path to input video file")
ap.add_argument("-c", "--confidence", type=float, default=0.5,
	help="minimum probability used to filter weak detections")
args = vars(ap.parse_args())

# initialize the colors list 
COLORS = np.random.uniform(0, 255, size=(args["num_classes"], 1))

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

# initialize variables to store frame dimensions
H = None
W = None

# if a video path was not supplied, grab a reference to the webcam
if not args.get("input", False):
	print("[INFO] starting video stream...")
	#vs = VideoStream(src=0).start()
	vs = VideoStream(src="nvarguscamerasrc ! video/x-raw(memory:NVMM), width=(int)1920, height=(int)1080,format=(string)NV12, framerate=(fraction)30/1 ! nvvidconv ! video/x-raw, format=(string)BGRx ! videoconvert ! video/x-raw, format=(string)BGR ! appsink").start()
	time.sleep(2.0)

# otherwise, grab a reference to the video file
else:
	print("[INFO] opening video file...")
	vs = cv2.VideoCapture(args["input"])

# start the frames per second throughput estimator
fps = FPS().start()

# loop over frames from the video file stream
while True:
	# grab the next frame and handle if we are reading from either
	# VideoCapture or VideoStream
	frame = vs.read()
	frame = frame[1] if args.get("input", False) else frame

	# if we are viewing a video and we did not grab a frame then we
	# have reached the end of the video
	if args["input"] is not None and frame is None:
		break

	# resize the frame to have a maximum width of 500 pixels
	frame = imutils.resize(frame, width=500)

	# check to see if the frame dimensions are not set
	if W is None or H is None:
		# set the frame dimensions
		(H, W) = frame.shape[:2]

	# grab a reference to the input image tensor and the
	# boxes
	imageTensor = tfSess.graph.get_tensor_by_name("image_tensor:0")
	boxesTensor = tfSess.graph.get_tensor_by_name("detection_boxes:0")

	# for each bounding box we would like to know the score
	# (i.e., probability) and class label
	scoresTensor = tfSess.graph.get_tensor_by_name("detection_scores:0")
	classesTensor = tfSess.graph.get_tensor_by_name("detection_classes:0")
	numDetections = tfSess.graph.get_tensor_by_name("num_detections:0")

	# prepare the image for detection
	image = cv2.cvtColor(frame.copy(), cv2.COLOR_BGR2RGB)
	image = np.expand_dims(image, axis=0)

	# perform inference and compute the bounding boxes,
	# probabilities, and class labels
	(boxes, scores, labels, N) = tfSess.run(
	  [boxesTensor, scoresTensor, classesTensor, numDetections],
	  feed_dict={imageTensor: image})

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
		cv2.rectangle(frame, (startX, startY), (endX, endY),
			COLORS[idx], 2)
		y = startY - 10 if startY - 10 > 10 else startY + 10
		cv2.putText(frame, label, (startX, y),
			cv2.FONT_HERSHEY_SIMPLEX, 0.3, COLORS[idx], 1)
		print(label)
		#print(i)
		#if i>20:			
			#name.append(label)
		#if i==40:
			#with open('output.csv', 'w', newline='') as csvfile:
				#writer = csv.writer(csvfile)
				#writer.writerow(name)
			#uploadFile()
			#transport = paramiko.Transport(('172.20.10.2', 22))
			#transport.connect(username='weiwei', password='c13447')
			#sftp = paramiko.SFTPClient.from_transport(transport)
			#sftp.put('/home/cc/Downloads/nano/output.csv', '/Users/weiwei/output.csv')
			
		#i+=1
		
	# show the output frame
	cv2.imshow("Frame", frame)
	key = cv2.waitKey(1) & 0xFF

	# if the `q` key was pressed, break from the loop
	if key == ord("q"):
		break

	# update the FPS counter
	fps.update()
 
# stop the timer and display FPS information
fps.stop()
print("[INFO] elapsed time: {:.2f}".format(fps.elapsed()))
print("[INFO] approx. FPS: {:.2f}".format(fps.fps()))

# if we are not using a video file, stop the camera video stream
if not args.get("input", False):
	vs.stop()

# otherwise, release the video file pointer
else:
	vs.release()

# close any open windows
cv2.destroyAllWindows() 
