"""
=========================================================
AegisSight AI Engine

Author : Joshika Parijat

Description:
    Central AI pipeline orchestrator.

=========================================================
"""

from typing import Dict, Any
import numpy as np

from ai.detection.detector import VehicleDetector
from ai.detection.vehicle_crop import VehicleCropper


class AegisSightEngine:
    """
    Main AI Engine.

    Future Pipeline

    CCTV Frame
        │
        ▼
    Vehicle Detection
        │
        ▼
    Vehicle Cropping
        │
        ▼
    DINOv2 Embedding
        │
        ▼
    Cross-view Matching
        │
        ▼
    Re-Identification
        │
        ▼
    Tracking
        │
        ▼
    Navigation
    """

    def __init__(self):

        print("=" * 60)
        print("Initializing AegisSight AI Engine")
        print("=" * 60)

        self.detector = VehicleDetector()

        self.cropper = VehicleCropper()

        print("✓ Vehicle Detector Loaded")

        print("✓ Vehicle Cropper Loaded")

        print("=" * 60)

    def process(
        self,
        frame: np.ndarray,
    ) -> Dict[str, Any]:

        detections = self.detector.detect(frame)

        crops = self.cropper.crop(
            frame,
            detections,
        )

        return {

            "detections": detections,

            "crops": crops,

            "num_detections": len(detections),

            "num_crops": len(crops),

        }