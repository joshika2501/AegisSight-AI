from ai.crossview.temporal_filter import TemporalFilter


def main():

    temporal = TemporalFilter()

    result = temporal.validate(

        source_timestamp=10.0,

        target_timestamp=15.2,

    )

    print("=" * 60)

    print("TEMPORAL FILTER TEST")

    print("=" * 60)

    print("Valid     :", result.valid)

    print("Gap       :", result.time_gap)

    print("Reason    :", result.reason)

    print("=" * 60)


if __name__ == "__main__":
    main()