"""
=========================================================
AegisSight AI
Vehicle Detection Module

Uses:
- YOLO11 (Ultralytics)
- Shared Detection Models
- Configuration Loader
=========================================================
"""

from pathlib import Path
from typing import List

import cv2
from ultralytics import YOLO

from ai.configs.config_loader import ConfigLoader
from ai.core.models import BoundingBox, Detection
from ai.utils.device import get_device
from ai.utils.logger import get_logger


class VehicleDetector:
    """
    Production-ready vehicle detector using YOLO11.
    """

    VEHICLE_CLASSES = {
        "car",
        "truck",
        "bus",
        "motorcycle",
        "bicycle",
    }

    def __init__(self):

        self.logger = get_logger(self.__class__.__name__)

        self.config = ConfigLoader()

        self.device = get_device()

        self.model_name = self.config.get(
            "detector",
            "model",
        )

        self.confidence = self.config.get(
            "detector",
            "confidence",
        )

        self.iou = self.config.get(
            "detector",
            "iou",
        )

        self.max_det = self.config.get(
            "detector",
            "max_detections",
        )

        self.logger.info(
            f"Loading YOLO model: {self.model_name}"
        )

        self.model = YOLO(self.model_name)

        self.logger.info(
            f"Running on device: {self.device}"
        )