"""
=========================================================
AegisSight AI

DINOv2 Feature Encoder

Author : Joshika Parijat
=========================================================
"""

import logging
import numpy as np
import torch
import torch.nn.functional as F
from transformers import AutoImageProcessor, Dinov2Model

from ai.utils.device import get_device


class DINOv2Encoder:
    """
    DINOv2 feature encoder for vehicle re-identification.
    """

    def __init__(
        self,
        model_name: str = "facebook/dinov2-base",
    ):

        self.logger = logging.getLogger("DINOv2Encoder")

        self.device = get_device()

        self.logger.info(f"Loading DINOv2 model: {model_name}")

        self.processor = AutoImageProcessor.from_pretrained(model_name)

        self.model = Dinov2Model.from_pretrained(model_name)

        self.model.to(self.device)

        self.model.eval()

        self.logger.info(f"DINOv2 running on {self.device}")

    @torch.no_grad()
    def encode(
        self,
        image: np.ndarray,
    ) -> np.ndarray:
        """
        Generate a normalized feature embedding.

        Parameters
        ----------
        image : np.ndarray

        Returns
        -------
        np.ndarray
            L2-normalized feature vector.
        """

        if image is None:
            raise ValueError("Input image is None.")

        inputs = self.processor(
            images=image,
            return_tensors="pt",
        )

        inputs = {
            k: v.to(self.device)
            for k, v in inputs.items()
        }

        outputs = self.model(**inputs)

        embedding = outputs.last_hidden_state[:, 0]

        embedding = F.normalize(
            embedding,
            p=2,
            dim=1,
        )

        return embedding.squeeze().cpu().numpy()