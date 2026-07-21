# AegisSight-AI

AegisSight-AI is a real-time AI surveillance system that uses computer vision to monitor live video feeds, detect objects, track movement, monitor restricted areas, and generate intelligent security alerts.

## Features

* Real-time object detection using YOLO
* Multi-object tracking using ByteTrack
* Crowd counting
* Restricted zone monitoring
* Intrusion detection
* Risk assessment engine
* AI-generated incident summary

## Tech Stack

* Python
* OpenCV
* YOLOv11
* ByteTrack
* FastAPI (Planned)

## Project Structure

```text
AegisSight-AI/
│
├── src/
│   ├── detection/
│   ├── tracking/
│   ├── analytics/
│   ├── api/
│   └── utils/
│
├── tests/
├── requirements.txt
└── README.md
```

## Installation

Clone the repository:

```bash
git clone https://github.com/joshika2501/AegisSight-AI.git
cd AegisSight-AI
```

Create a virtual environment:

```bash
python -m venv venv
```

Activate the virtual environment:

**Windows**

```bash
venv\Scripts\activate
```

Install the required packages:

```bash
pip install -r requirements.txt
```

## Run

Run the tracking module:

```bash
python src/tracking/bytetrack.py
```

## Current Status

* ✅ Object Detection
* ✅ Object Tracking
* ✅ Crowd Counting
* ✅ Zone Monitoring
* ✅ Intrusion Detection
* ✅ Risk Assessment
* ✅ Incident Summary

## Future Improvements

* Fire and Smoke Detection
* Weapon Detection
* Dashboard Integration
* Backend API
* Multi-camera Support

## Team

Developed as a hackathon project.
