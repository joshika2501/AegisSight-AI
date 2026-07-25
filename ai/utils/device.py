"""
Device Utility
"""

import torch


def get_device() -> str:
    """
    Returns the best available device.
    """

    if torch.cuda.is_available():
        return "cuda"

    return "cpu"