"""
=========================================================
AegisSight AI

Image Preprocessing for DINOv2

Author : Joshika Parijat
=========================================================
"""

from PIL import Image
import numpy as np
import torch
from torchvision import transforms


class ImagePreprocessor:
    """
    Prepares vehicle crops for DINOv2.
    """

    def __init__(self, image_size: int = 224):

        self.image_size = image_size

        self.transform = transforms.Compose([
            transforms.ToPILImage(),
            transforms.Resize((image_size, image_size)),
            transforms.ToTensor(),
            transforms.Normalize(
                mean=[0.485, 0.456, 0.406],
                std=[0.229, 0.224, 0.225]
            )
        ])

    def preprocess(self, image: np.ndarray) -> torch.Tensor:
        """
        Convert OpenCV image into DINOv2 input tensor.

        Parameters
        ----------
        image : np.ndarray
            RGB or BGR vehicle crop.

        Returns
        -------
        torch.Tensor
            Shape: (1, 3, image_size, image_size)
        """

        if image is None:
            raise ValueError("Input image is None.")

        if not isinstance(image, np.ndarray):
            raise TypeError("Input must be a numpy array.")

        tensor = self.transform(image)

        return tensor.unsqueeze(0)