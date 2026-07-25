"""
=========================================================
AegisSight AI

ByteTrack Wrapper

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from typing import List

import numpy as np

from ai.core.models import Detection
from ai.utils.logger import get_logger


class ByteTrackWrapper:
    """
    Wrapper around ByteTrack.

    This class provides a consistent interface for the
    AegisSight pipeline, independent of the underlying
    tracking algorithm.
    """

    def __init__(
        self,
        track_thresh: float = 0.5,
        match_thresh: float = 0.8,
        track_buffer: int = 30,
    ):

        self.logger = get_logger("ByteTrack")

        self.track_thresh = track_thresh
        self.match_thresh = match_thresh
        self.track_buffer = track_buffer

        # Placeholder for the actual ByteTrack tracker
        self.tracker = None

        self.logger.info("ByteTrack wrapper initialized.")

    def initialize(self):
        """
        Initialize the underlying ByteTrack tracker.
        """

        # This will be replaced with the official
        # ByteTrack implementation later.

        self.logger.info("Tracker initialized.")

    def update(
        self,
        detections: List[Detection],
        frame: np.ndarray,
    ):
        """
        Update tracker using current frame detections.

        Parameters
        ----------
        detections : List[Detection]

        frame : np.ndarray

        Returns
        -------
        List
            Active tracks.
        """

        if self.tracker is None:
            self.initialize()

        # Placeholder
        active_tracks = []

        return active_tracks