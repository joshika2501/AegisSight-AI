import numpy as np

from ai.crossview.association import AssociationEngine
from ai.tracking.track_manager import VehicleTrack


def main():

    track_a = VehicleTrack(

        track_id=1,

        class_name="car",

        confidence=0.98,

        embedding=np.random.rand(768),

    )

    track_b = VehicleTrack(

        track_id=8,

        class_name="car",

        confidence=0.96,

        embedding=np.random.rand(768),

    )

    engine = AssociationEngine()

    result = engine.associate(

        source_track=track_a,

        target_track=track_b,

        similarity=0.95,

    )

    print("=" * 60)

    print("ASSOCIATION TEST")

    print("=" * 60)

    print("Matched    :", result.matched)

    print("Confidence :", round(result.confidence, 3))

    print("Reason     :", result.reason)

    print(
        "Source ID  :",
        result.source_track.track_id,
    )

    print(
        "Target ID  :",
        result.target_track.track_id,
    )

    print("=" * 60)


if __name__ == "__main__":
    main()