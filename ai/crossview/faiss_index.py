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

from ai.core.models import FeatureRecord, MatchResult
from ai.utils.logger import get_logger


class FAISSIndex:
    """
    FAISS index for storing and searching vehicle feature
    embeddings.

    Uses cosine similarity by indexing L2-normalized vectors
    with IndexFlatIP (Inner Product).
    """

    def __init__(self, dimension: int = 768):

        self.logger = get_logger("FAISSIndex")

        self.dimension = dimension

        # Cosine similarity using normalized vectors
        self.index = faiss.IndexFlatIP(dimension)

        # Maps FAISS indices to FeatureRecord objects
        self.records: List[FeatureRecord] = []

        self.logger.info(
            f"Initialized FAISS Index (dimension={dimension})"
        )

    def add(
        self,
        record: FeatureRecord,
    ) -> None:
        """
        Add a FeatureRecord to the FAISS index.
        """

        vector = record.embedding.astype(np.float32)

        if vector.ndim != 1:
            raise ValueError(
                "Embedding must be a 1-D numpy array."
            )

        if vector.shape[0] != self.dimension:
            raise ValueError(
                f"Expected embedding dimension "
                f"{self.dimension}, got {vector.shape[0]}"
            )

        # Normalize before indexing
        vector = vector / np.linalg.norm(vector)

        self.index.add(vector.reshape(1, -1))

        self.records.append(record)

        self.logger.info(
            f"Indexed vehicle: {record.vehicle_id}"
        )

    def add_batch(
        self,
        records: List[FeatureRecord],
    ) -> None:
        """
        Add multiple FeatureRecords.
        """

        for record in records:
            self.add(record)

    def search(
        self,
        embedding: np.ndarray,
        k: int = 5,
    ) -> List[MatchResult]:
        """
        Search Top-K similar vehicles.

        Parameters
        ----------
        embedding : np.ndarray
            Query embedding.

        k : int
            Number of nearest neighbours.

        Returns
        -------
        List[MatchResult]
        """

        if self.size() == 0:
            return []

        query = embedding.astype(np.float32)

        if query.ndim != 1:
            raise ValueError(
                "Query embedding must be 1-D."
            )

        if query.shape[0] != self.dimension:
            raise ValueError(
                f"Expected dimension "
                f"{self.dimension}, got {query.shape[0]}"
            )

        # Normalize query
        query = query / np.linalg.norm(query)

        scores, indices = self.index.search(
            query.reshape(1, -1),
            min(k, self.size())
        )

        results: List[MatchResult] = []

        for score, idx in zip(scores[0], indices[0]):

            if idx < 0:
                continue

            results.append(

                MatchResult(

                    record=self.records[idx],

                    score=float(score)

                )

            )

        return results

    def size(self) -> int:
        """
        Returns number of indexed vehicles.
        """

        return len(self.records)

    def clear(self) -> None:
        """
        Remove every indexed feature.
        """

        self.index.reset()

        self.records.clear()

        self.logger.info("FAISS index cleared.")