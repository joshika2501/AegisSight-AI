"""
=========================================================
AegisSight AI

Camera Connectivity Graph

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from collections import defaultdict
from typing import Dict, List

from ai.utils.logger import get_logger


class CameraGraph:
    """
    Represents connectivity between surveillance cameras.
    """

    def __init__(self):

        self.logger = get_logger("CameraGraph")

        self.graph: Dict[str, List[str]] = defaultdict(list)

    def add_connection(
        self,
        source: str,
        destination: str,
        bidirectional: bool = True,
    ) -> None:

        if destination not in self.graph[source]:
            self.graph[source].append(destination)

        if bidirectional:

            if source not in self.graph[destination]:
                self.graph[destination].append(source)

    def are_connected(
        self,
        source: str,
        destination: str,
    ) -> bool:

        return destination in self.graph.get(source, [])

    def neighbours(
        self,
        camera: str,
    ) -> List[str]:

        return list(self.graph.get(camera, []))

    def number_of_cameras(self) -> int:

        return len(self.graph)

    def clear(self) -> None:

        self.graph.clear()