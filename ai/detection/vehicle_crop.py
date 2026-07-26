"""
=========================================================
AegisSight AI
Vehicle Crop Module

Author: Joshika Parijat

Description:
    Extracts cropped vehicle images from detections.
=========================================================
"""

from typing import List
import cv2
import numpy as np

from ai.core.models import Detection, VehicleCrop


class VehicleCropper:
    """
    Crops detected vehicles from an image.
    """

    def __init__(self):
        pass

    def crop(
        self,
        frame: np.ndarray,
        detections: List[Detection],
    ) -> List[VehicleCrop]:

        crops = []

        height, width = frame.shape[:2]

        for detection in detections:

            box = detection.bbox

            x1 = max(0, box.x1)
            y1 = max(0, box.y1)
            x2 = min(width, box.x2)
            y2 = min(height, box.y2)

            crop = frame[y1:y2, x1:x2]

            if crop.size == 0:
                continue

            crops.append(
                VehicleCrop(
                    image=crop,
                    detection=detection,
                )
            )

        return crops

    def save_crops(
        self,
        crops: List[VehicleCrop],
        output_dir: str = "outputs/crops",
    ):

        import os

        os.makedirs(output_dir, exist_ok=True)

        for index, crop in enumerate(crops):

            filename = (
                f"{output_dir}/vehicle_{index}.jpg"
            )

            cv2.imwrite(
                filename,
                crop.image,
            )