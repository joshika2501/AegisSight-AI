"""
=========================================================
AegisSight AI

Tracking Pipeline

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from typing import Any, Dict, List

import numpy as np

from ai.crossview.faiss_index import FAISSIndex
from ai.crossview.matcher import CrossViewMatcher
from ai.core.models import Detection, FeatureRecord
from ai.embedding.feature_extractor import FeatureExtractor
from ai.tracking.bytetrack import ByteTrackWrapper
from ai.utils.logger import get_logger


class TrackingPipeline:
    """
    Main tracking pipeline for AegisSight.

    Responsibilities
    ----------------
    1. Extract feature embeddings
    2. Search FAISS database
    3. Match with existing vehicles
    4. Update tracker
    """

    def __init__(self):

        self.logger = get_logger("TrackingPipeline")

        self.feature_extractor = FeatureExtractor()

        self.faiss = FAISSIndex()

        self.matcher = CrossViewMatcher()

        self.tracker = ByteTrackWrapper()

        self.logger.info("Tracking pipeline initialized.")

    def process(
        self,
        detections: List[Detection],
        crops: List[np.ndarray],
        frame: np.ndarray,
    ) -> List[Dict[str, Any]]:

        results = []

        tracks = self.tracker.update(
            detections,
            frame,
        )

        for crop in crops:

            embedding = self.feature_extractor.extract(crop)

            matches = self.faiss.search(
                embedding,
                k=5,
            )

            match_result = self.matcher.match(matches)

            results.append(
                {
                    "match": match_result,
                    "embedding": embedding,
                }
            )

        return results
    