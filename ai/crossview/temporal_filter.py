"""
=========================================================
AegisSight AI

Temporal Association Filter

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from dataclasses import dataclass

from ai.utils.logger import get_logger


@dataclass
class TemporalResult:
    """
    Result returned by the temporal filter.
    """

    valid: bool

    time_gap: float

    reason: str


class TemporalFilter:
    """
    Checks whether two observations are
    temporally consistent.
    """

    def __init__(
        self,
        minimum_gap: float = 0.5,
        maximum_gap: float = 30.0,
    ):

        self.logger = get_logger("TemporalFilter")

        self.minimum_gap = minimum_gap

        self.maximum_gap = maximum_gap

    def validate(
        self,
        source_timestamp: float,
        target_timestamp: float,
    ) -> TemporalResult:

        gap = target_timestamp - source_timestamp

        if gap < self.minimum_gap:

            return TemporalResult(

                valid=False,

                time_gap=gap,

                reason="Vehicle appeared too quickly."

            )

        if gap > self.maximum_gap:

            return TemporalResult(

                valid=False,

                time_gap=gap,

                reason="Vehicle appeared too late."

            )

        return TemporalResult(

            valid=True,

            time_gap=gap,

            reason="Temporal constraint satisfied."

        )