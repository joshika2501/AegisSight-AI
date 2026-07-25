"""
=========================================================
AegisSight AI

Cross-View Vehicle Matcher

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from typing import List

from ai.core.models import MatchResult
from ai.utils.logger import get_logger


class CrossViewMatcher:
    """
    Decision layer for vehicle matching.

    Uses similarity scores returned by FAISS to classify
    candidate matches into:

        - Confirmed Match
        - Probable Match
        - No Match
    """

    def __init__(
        self,
        confirmed_threshold: float = 0.92,
        probable_threshold: float = 0.80,
    ):

        self.logger = get_logger("CrossViewMatcher")

        self.confirmed_threshold = confirmed_threshold
        self.probable_threshold = probable_threshold

        self.logger.info(
            "CrossViewMatcher initialized "
            f"(confirmed={confirmed_threshold}, "
            f"probable={probable_threshold})"
        )

    def match(
        self,
        candidates: List[MatchResult],
    ):
        """
        Evaluate FAISS search results.

        Parameters
        ----------
        candidates : List[MatchResult]

        Returns
        -------
        dict
        """

        if len(candidates) == 0:

            return {
                "status": "NO_MATCH",
                "best_match": None,
                "score": 0.0,
                "candidates": []
            }

        best = candidates[0]

        if best.score >= self.confirmed_threshold:

            status = "CONFIRMED_MATCH"

        elif best.score >= self.probable_threshold:

            status = "PROBABLE_MATCH"

        else:

            status = "NO_MATCH"

        return {

            "status": status,

            "best_match": best.record,

            "score": best.score,

            "candidates": candidates

        }