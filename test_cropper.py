import cv2

from ai.detection.detector import VehicleDetector
from ai.detection.vehicle_crop import VehicleCropper


def main():

    detector = VehicleDetector()

    cropper = VehicleCropper()

    image = cv2.imread("sample_data/road.jpg")

    detections = detector.detect(image)

    crops = cropper.crop(
        image,
        detections,
    )

    print("=" * 40)
    print(f"Detected Vehicles : {len(detections)}")
    print(f"Cropped Vehicles  : {len(crops)}")
    print("=" * 40)

    cropper.save_crops(crops)

    print("Vehicle crops saved to outputs/crops")


if __name__ == "__main__":
    main()