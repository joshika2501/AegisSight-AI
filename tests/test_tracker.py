import numpy as np

from ai.tracking.tracker import TrackingPipeline


def main():

    pipeline = TrackingPipeline()

    frame = np.zeros((720, 1280, 3), dtype=np.uint8)

    crops = [
        np.zeros((224, 224, 3), dtype=np.uint8)
    ]

    results = pipeline.process(
        detections=[],
        crops=crops,
        frame=frame,
    )

    print("=" * 60)
    print("TRACKING PIPELINE TEST")
    print("=" * 60)

    print("Processed Vehicles :", len(results))

    if results:
        print(results[0]["match"]["status"])

    print("=" * 60)


if __name__ == "__main__":
    main()