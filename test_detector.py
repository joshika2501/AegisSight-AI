import cv2

from ai.detection.detector import VehicleDetector


def main():

    detector = VehicleDetector()

    image = cv2.imread("sample_data/road.jpg")

    if image is None:
        raise FileNotFoundError(
            "sample_data/road.jpg not found."
        )

    detections = detector.detect(image)

    print("=" * 40)
    print(f"Vehicles Detected : {len(detections)}")
    print("=" * 40)

    output = detector.draw_detections(
        image,
        detections,
    )

    cv2.imwrite(
        "output.jpg",
        output,
    )

    print("Output saved as output.jpg")


if __name__ == "__main__":
    main()