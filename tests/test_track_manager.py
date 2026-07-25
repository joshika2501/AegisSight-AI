import numpy as np

from ai.tracking.track_manager import (
    TrackManager,
    VehicleTrack,
)


def main():

    manager = TrackManager()

    track = VehicleTrack(

        track_id=1,

        class_name="car",

        confidence=0.97,

    )

    manager.add_track(track)

    manager.update_track(

        track_id=1,

        position=(120, 250),

        confidence=0.98,

        embedding=np.random.rand(768),

        camera="CCTV_01",

        frame_id=15,

    )

    print("=" * 60)

    print("TRACK MANAGER TEST")

    print("=" * 60)

    print(
        "Total Tracks:",
        manager.number_of_tracks(),
    )

    vehicle = manager.get_track(1)

    print(
        "Vehicle:",
        vehicle.class_name,
    )

    print(
        "History:",
        vehicle.history,
    )

    print(
        "Last Camera:",
        vehicle.last_camera,
    )

    print("=" * 60)


if __name__ == "__main__":
    main()