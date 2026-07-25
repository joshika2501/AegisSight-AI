"""
=========================================================
AegisSight AI

Vehicle Feature Extractor

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from typing import List

import numpy as np

from ai.embedding.preprocess import ImagePreprocessor
from ai.embedding.dinov2_encoder import DINOv2Encoder


class FeatureExtractor:
    """
    High-level feature extraction interface.

    Pipeline
    --------
    Vehicle Crop
            │
            ▼
    Preprocess
            │
            ▼
    DINOv2
            │
            ▼
    Feature Vector
    """

    def __init__(self):

        self.preprocessor = ImagePreprocessor()

        self.encoder = DINOv2Encoder()

    def extract(
        self,
        image: np.ndarray,
    ) -> np.ndarray:
        """
        Extract embedding from one vehicle image.
        """

        return self.encoder.encode(image)

    def extract_batch(
        self,
        images: List[np.ndarray],
    ) -> np.ndarray:
        """
        Extract embeddings from multiple images.
        """

        return self.encoder.encode_batch(images)