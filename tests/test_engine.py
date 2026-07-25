import cv2

from ai.engine import AegisSightEngine


def main():

    engine = AegisSightEngine()

    image = cv2.imread("sample_data/road.jpg")

    if image is None:

        raise FileNotFoundError(
            "sample_data/road.jpg not found."
        )

    result = engine.process(image)

    print()

    print("=" * 60)

    print("ENGINE OUTPUT")

    print("=" * 60)

    print("Detections :", result["num_detections"])

    print("Crops      :", result["num_crops"])

    print("=" * 60)


if __name__ == "__main__":
    main()