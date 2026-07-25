"""
=========================================================
AegisSight AI

Shared Data Models

Author: Joshika Parijat
=========================================================
"""

from dataclasses import dataclass, field
from typing import List, Optional
import numpy as np


# --------------------------------------------------------
# Bounding Box
# --------------------------------------------------------

@dataclass
class BoundingBox:
    x1: int
    y1: int
    x2: int
    y2: int

    @property
    def width(self):
        return self.x2 - self.x1

    @property
    def height(self):
        return self.y2 - self.y1

    @property
    def center(self):
        return (
            int((self.x1 + self.x2) / 2),
            int((self.y1 + self.y2) / 2),
        )


# --------------------------------------------------------
# Vehicle Detection
# --------------------------------------------------------

@dataclass
class Detection:
    """
    Represents one detected vehicle.
    """

    bbox: BoundingBox

    confidence: float

    class_id: int

    class_name: str

    frame_id: int | None = None

    track_id: int | None = None


# --------------------------------------------------------
# Vehicle Crop
# --------------------------------------------------------
def track(
    self,
    frame,
    frame_id=None,
    persist: bool = True,
) -> List[Detection]:
    """
    Detect and track vehicles using YOLO + ByteTrack.
    """

    results = self.model.track(
        source=frame,
        conf=self.confidence,
        iou=self.iou,
        max_det=self.max_det,
        device=self.device,
        persist=persist,
        tracker="bytetrack.yaml",
        verbose=False,
    )

    detections = []

    for result in results:

        if result.boxes is None:
            continue

        for box in result.boxes:

            class_id = int(box.cls.item())

            class_name = self.model.names[class_id]

            if class_name not in self.VEHICLE_CLASSES:
                continue

            confidence = float(box.conf.item())

            x1, y1, x2, y2 = map(
                int,
                box.xyxy[0].tolist(),
            )

            track_id = None

            if box.id is not None:
                track_id = int(box.id.item())

            detections.append(

                Detection(

                    bbox=BoundingBox(
                        x1=x1,
                        y1=y1,
                        x2=x2,
                        y2=y2,
                    ),

                    confidence=confidence,

                    class_id=class_id,

                    class_name=class_name,

                    frame_id=frame_id,

                    track_id=track_id,

                )

            )

    self.logger.info(
        f"{len(detections)} tracked vehicle(s)."
    )

    return detections

@dataclass
class VehicleCrop:

    image: np.ndarray

    detection: Detection


# --------------------------------------------------------
# Feature Embedding
# --------------------------------------------------------

@dataclass
class FeatureEmbedding:

    vector: np.ndarray

    detection: Detection

    model_name: str


# --------------------------------------------------------
# Cross View Match
# --------------------------------------------------------

@dataclass
class MatchResult:

    cctv_track_id: int

    drone_track_id: int

    similarity: float

    matched: bool


# --------------------------------------------------------
# Tracking State
# --------------------------------------------------------

@dataclass
class TrackState:

    track_id: int

    bbox: BoundingBox

    confidence: float

    velocity: Optional[np.ndarray] = None

    age: int = 0

    lost: bool = False


# --------------------------------------------------------
# Predicted Position
# --------------------------------------------------------

@dataclass
class PredictedPosition:

    x: float

    y: float

    timestamp: float


# --------------------------------------------------------
# Navigation Waypoint
# --------------------------------------------------------

@dataclass
class Waypoint:

    latitude: float

    longitude: float

    altitude: float


# --------------------------------------------------------
# Drone Route
# --------------------------------------------------------

@dataclass
class DroneRoute:

    vehicle_id: int

    waypoints: List[Waypoint] = field(default_factory=list)


# --------------------------------------------------------
# AI Output
# --------------------------------------------------------

@dataclass
class AIResult:

    detections: List[Detection]

    matches: List[MatchResult]

    routes: List[DroneRoute]
    

# ---------------------------------------------------------
# Feature index record and search result
# ---------------------------------------------------------


@dataclass
class FeatureRecord:
    """Represents one indexed vehicle feature."""

    vehicle_id: str
    camera_id: str
    timestamp: float
    embedding: np.ndarray
    confidence: float = 1.0
    frame_id: Optional[int] = None
    bbox: Optional[tuple] = None  # (x1, y1, x2, y2)


@dataclass
class MatchResult:
    """Represents one similarity search result."""

    record: FeatureRecord
    score: float