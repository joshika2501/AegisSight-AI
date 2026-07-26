"""
=========================================================
AegisSight AI

Track Manager

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from dataclasses import dataclass, field
from typing import Dict, List, Optional

import numpy as np

from ai.utils.logger import get_logger


@dataclass
class VehicleTrack:
    """
    Stores information about one tracked vehicle.
    """

    track_id: int

    class_name: str

    confidence: float

    embedding: Optional[np.ndarray] = None

    first_frame: int = 0

    last_frame: int = 0

    first_camera: str = "UNKNOWN"

    last_camera: str = "UNKNOWN"

    history: List[tuple] = field(default_factory=list)


class TrackManager:
    """
    Maintains all active vehicle tracks.
    """

    def __init__(self):

        self.logger = get_logger("TrackManager")

        self.tracks: Dict[int, VehicleTrack] = {}

        self.logger.info("Track Manager initialized.")

    def add_track(
        self,
        track: VehicleTrack,
    ):

        self.tracks[track.track_id] = track

        self.logger.info(
            f"New Track Added : {track.track_id}"
        )

    def update_track(
        self,
        track_id: int,
        position: tuple,
        confidence: float,
        embedding=None,
        camera="UNKNOWN",
        frame_id=0,
    ):

        if track_id not in self.tracks:
            return

        track = self.tracks[track_id]

        track.history.append(position)

        track.confidence = confidence

        track.last_frame = frame_id

        track.last_camera = camera

        if embedding is not None:
            track.embedding = embedding

    def remove_track(
        self,
        track_id: int,
    ):

        if track_id in self.tracks:

            del self.tracks[track_id]

            self.logger.info(
                f"Track Removed : {track_id}"
            )

    def get_track(
        self,
        track_id: int,
    ) -> Optional[VehicleTrack]:

        return self.tracks.get(track_id)

    def get_all_tracks(self):

        return list(self.tracks.values())

    def number_of_tracks(self):

        return len(self.tracks)

    def clear(self):

        self.tracks.clear()

        self.logger.info(
            "All tracks cleared."
        )