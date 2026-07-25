"""
=========================================================
AegisSight AI

Cross-Camera Association Engine

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from dataclasses import dataclass
from typing import Optional

from ai.tracking.track_manager import VehicleTrack
from ai.utils.logger import get_logger


@dataclass
class AssociationResult:
    """
    Result of cross-camera association.
    """

    matched: bool

    confidence: float

    reason: str

    source_track: Optional[VehicleTrack]

    target_track: Optional[VehicleTrack]


class AssociationEngine:
    """
    Determines whether two vehicle tracks
    correspond to the same physical vehicle.
    """

    def __init__(
        self,
        similarity_threshold: float = 0.92,
    ):

        self.logger = get_logger("AssociationEngine")

        self.similarity_threshold = similarity_threshold

        self.logger.info(
            f"Association threshold = {similarity_threshold}"
        )

    def associate(
        self,
        source_track: VehicleTrack,
        target_track: VehicleTrack,
        similarity: float,
    ) -> AssociationResult:

        if similarity >= self.similarity_threshold:

            self.logger.info(
                f"Track {source_track.track_id} "
                f"matched with "
                f"{target_track.track_id}"
            )

            return AssociationResult(

                matched=True,

                confidence=similarity,

                reason="Appearance similarity above threshold.",

                source_track=source_track,

                target_track=target_track,

            )

        self.logger.info(
            f"Track {source_track.track_id} "
            f"not associated."
        )

        return AssociationResult(

            matched=False,

            confidence=similarity,

            reason="Similarity below threshold.",

            source_track=source_track,

            target_track=target_track,

        )