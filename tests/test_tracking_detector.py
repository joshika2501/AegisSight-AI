import cv2
import os

from ai.detection.detector import VehicleDetector


def main():

    detector = VehicleDetector()

    image_path = "sample_data/road.jpg"

    if not os.path.exists(image_path):

        print(f"Image not found: {image_path}")

        return

    frame = cv2.imread(image_path)

    if frame is None:

        print("OpenCV could not read the image.")

        return

    detections = detector.track(frame)

    print("=" * 60)
    print("TRACKING TEST")
    print("=" * 60)

    for detection in detections:

        print(f"Track ID   : {detection.track_id}")
        print(f"Vehicle    : {detection.class_name}")
        print(f"Confidence : {detection.confidence:.3f}")
        print("-" * 40)

    image = detector.draw_detections(
        frame,
        detections,
    )

    cv2.imshow(
        "AegisSight Tracking",
        image,
    )

    cv2.waitKey(0)

    cv2.destroyAllWindows()


if __name__ == "__main__":
    main()