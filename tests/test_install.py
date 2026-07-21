import cv2
import torch
from ultralytics import YOLO

print("=" * 50)
print("AegisSight AI Environment Test")
print("=" * 50)

print("OpenCV Version :", cv2.__version__)
print("PyTorch Version:", torch.__version__)
print("CUDA Available :", torch.cuda.is_available())

print("\nLoading YOLO model...")
model = YOLO("yolo11n.pt")

print("YOLO loaded successfully!")
print("Environment setup completed successfully!")