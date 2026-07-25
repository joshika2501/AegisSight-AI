"""
=========================================================
AegisSight AI
DINOv2 Feature Encoder

Author : Joshika Parijat
=========================================================
"""

from __future__ import annotations

from typing import Union

import numpy as np
import torch
import torch.nn.functional as F
from PIL import Image
from transformers import AutoImageProcessor, Dinov2Model

from ai.utils.device import get_device
from ai.utils.logger import get_logger


class DINOv2Encoder:
    """
    DINOv2 feature encoder.

    Produces an L2-normalized embedding suitable for
    similarity search (FAISS) and vehicle matching.
    """

    def __init__(
        self,
        model_name: str = "facebook/dinov2-base",
    ):

        self.logger = get_logger("DINOv2Encoder")

        self.device = get_device()

        self.logger.info(f"Loading model : {model_name}")

        self.processor = AutoImageProcessor.from_pretrained(model_name)

        self.model = Dinov2Model.from_pretrained(model_name)

        self.model.to(self.device)

        self.model.eval()

        self.logger.info(f"Running on {self.device}")

    @torch.no_grad()
    def encode(
        self,
        image: Union[np.ndarray, Image.Image],
    ) -> np.ndarray:
        """
        Encode a single image.

        Parameters
        ----------
        image : numpy.ndarray | PIL.Image

        Returns
        -------
        numpy.ndarray
            Normalized feature vector.
        """

        if image is None:
            raise ValueError("Input image is None.")

        if isinstance(image, np.ndarray):
            image = Image.fromarray(image[:, :, ::-1])

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

        return embedding.squeeze(0).cpu().numpy()

    @torch.no_grad()
    def encode_batch(
        self,
        images,
    ) -> np.ndarray:
        """
        Encode multiple images.
        """

        pil_images = []

        for img in images:

            if isinstance(img, np.ndarray):
                img = Image.fromarray(img[:, :, ::-1])

            pil_images.append(img)

        inputs = self.processor(
            images=pil_images,
            return_tensors="pt",
        )

        inputs = {
            k: v.to(self.device)
            for k, v in inputs.items()
        }

        outputs = self.model(**inputs)

        embeddings = outputs.last_hidden_state[:, 0]

        embeddings = F.normalize(
            embeddings,
            p=2,
            dim=1,
        )

        return embeddings.cpu().numpy()