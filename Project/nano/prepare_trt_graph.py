# USAGE
# python prepare_trt_graph.py --ckpt model/model_checkpoints/model.ckpt-16252 \
#	--config model/ssd_inception_v2_pets.config --trt-graph model/weapons_model_trt.pb

# import the necessary packages
from tf_trt_models.detection import build_detection_graph
from tensorflow.python.util import deprecation
import tensorflow.contrib.tensorrt as trt
import tensorflow as tf
import argparse
import os

# construct the argument parser and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-c1", "--ckpt", required=True, 
	help="path to the checkpoint files of the detector model")
ap.add_argument("-c2", "--config", required=True, 
	help="path to the configurations of the detector model")
ap.add_argument("-t", "--trt-graph", required=True, 
	help="path where the trt graph will be saved")
args = vars(ap.parse_args())

# turn off the deprecation warnings and logs to keep the
# console clean for convenience
deprecation._PRINT_DEPRECATION_WARNINGS = False
os.environ["TF_CPP_MIN_LOG_LEVEL"] = "3"

# load the checkpoint files and build the frozen graph
print("[INFO] building frozen graph...")
(frozenGraph, _ , outputNames) = build_detection_graph(
	config=args["config"],
	checkpoint=args["ckpt"])

# create the optimized TRT graph from 
# the frozen TensorFlow graph
print("[INFO] creating TRT graph...")
trtGraph = trt.create_inference_graph(
	input_graph_def=frozenGraph,
	outputs=outputNames,
	max_batch_size=1,
	max_workspace_size_bytes=1 << 25,
	precision_mode="FP16",
	minimum_segment_size=50)

# serialize the TRT graph
print("[INFO] serializing TRT graph...")
with open(args["trt_graph"], "wb") as f:
	f.write(trtGraph.SerializeToString())
