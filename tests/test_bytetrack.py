import numpy as np

from ai.tracking.bytetrack import ByteTrackWrapper


def main():

    tracker = ByteTrackWrapper()

    frame = np.zeros((720, 1280, 3), dtype=np.uint8)

    tracks = tracker.update([], frame)

    print("=" * 60)

    print("BYTE TRACK TEST")

    print("=" * 60)

    print("Tracker initialized successfully.")

    print("Returned Tracks:", tracks)

    print("=" * 60)


if __name__ == "__main__":
    main()