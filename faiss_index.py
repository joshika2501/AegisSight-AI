"""
=========================================================
AegisSight AI

FAISS Feature Index

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from typing import List

import faiss
import numpy as np

from ai.core.models import FeatureRecord
from ai.utils.logger import get_logger


class FAISSIndex:
    """
    Stores and searches vehicle feature embeddings.
    """

    def __init__(self, dimension: int = 768):

        self.logger = get_logger("FAISSIndex")

        self.dimension = dimension

        self.index = faiss.IndexFlatIP(dimension)

        self.records: List[FeatureRecord] = []

        self.logger.info(
            f"Initialized FAISS Index (dimension={dimension})"
        )

    def add(
        self,
        record: FeatureRecord,
    ) -> None:
        """
        Add one feature record to the index.
        """

        vector = record.embedding.astype(np.float32)

        vector = vector.reshape(1, -1)

        self.index.add(vector)

        self.records.append(record)

    def size(self) -> int:
        """
        Number of indexed vehicles.
        """

        return len(self.records)

    def search(
        self,
        embedding: np.ndarray,
        k: int = 5,
    ) -> List[FeatureRecord]:
        """
        Search the Top-K nearest neighbours.
        """

        if self.size() == 0:
            return []

        query = embedding.astype(np.float32)

        query = query.reshape(1, -1)

        _, indices = self.index.search(query, k)

        matches = []

        for idx in indices[0]:

            if idx == -1:
                continue

            matches.append(self.records[idx])

        return matches