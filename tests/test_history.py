from ai.tracking.history import TrackHistory


def main():

    history = [

        (100, 200),

        (105, 204),

        (110, 208),

        (118, 215),

        (126, 223),

    ]

    print("=" * 60)

    print("TRACK HISTORY TEST")

    print("=" * 60)

    print(
        "Distance :",
        round(
            TrackHistory.travelled_distance(history),
            2,
        ),
    )

    print(
        "Speed :",
        round(
            TrackHistory.average_speed(history),
            2,
        ),
    )

    print(
        "Heading :",
        round(
            TrackHistory.heading(history),
            2,
        ),
    )

    print(
        "Latest Position :",
        TrackHistory.latest_position(history),
    )

    print("=" * 60)


if __name__ == "__main__":
    main()