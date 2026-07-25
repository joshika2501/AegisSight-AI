"""
=========================================================
AegisSight AI

Track History Utilities

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from math import atan2, degrees, sqrt


class TrackHistory:
    """
    Utility functions for analysing
    tracked vehicle trajectories.
    """

    @staticmethod
    def travelled_distance(history):

        if len(history) < 2:
            return 0.0

        distance = 0.0

        for i in range(1, len(history)):

            x1, y1 = history[i - 1]

            x2, y2 = history[i]

            distance += sqrt(
                (x2 - x1) ** 2 +
                (y2 - y1) ** 2
            )

        return distance

    @staticmethod
    def average_speed(
        history,
        fps=30,
    ):

        if len(history) < 2:
            return 0.0

        distance = TrackHistory.travelled_distance(
            history
        )

        time_seconds = len(history) / fps

        return distance / time_seconds

    @staticmethod
    def heading(history):

        if len(history) < 2:
            return None

        x1, y1 = history[-2]

        x2, y2 = history[-1]

        angle = degrees(
            atan2(
                y2 - y1,
                x2 - x1,
            )
        )

        return angle

    @staticmethod
    def latest_position(history):

        if len(history) == 0:
            return None

        return history[-1]