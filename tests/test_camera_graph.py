from ai.crossview.camera_graph import CameraGraph


def main():

    graph = CameraGraph()

    graph.add_connection("Gate", "Parking A")
    graph.add_connection("Parking A", "Parking B")
    graph.add_connection("Parking B", "Exit")

    print("=" * 60)
    print("CAMERA GRAPH TEST")
    print("=" * 60)

    print(
        "Gate -> Parking A:",
        graph.are_connected(
            "Gate",
            "Parking A",
        ),
    )

    print(
        "Gate -> Exit:",
        graph.are_connected(
            "Gate",
            "Exit",
        ),
    )

    print()

    print(
        "Neighbours of Parking A:",
        graph.neighbours("Parking A"),
    )

    print()

    print(
        "Number of Cameras:",
        graph.number_of_cameras(),
    )

    print("=" * 60)


if __name__ == "__main__":
    main()