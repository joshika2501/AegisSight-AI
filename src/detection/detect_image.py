from ultralytics import YOLO
import cv2
import glob

model = YOLO("yolo11n.pt")

images = glob.glob("datasets/Car-Bike-Dataset/Bike/*.jpg")

if len(images) == 0:
    print("No images found!")
    exit()

image_path = images[0]

results = model(image_path)

annotated = results[0].plot()

cv2.imshow("Detection", annotated)
cv2.waitKey(0)
cv2.destroyAllWindows()