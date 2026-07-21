from ultralytics import YOLO
import cv2

model = YOLO("yolo11n.pt")

cap = cv2.VideoCapture(0)

# Restricted Zone
ZONE = (300, 400, 950, 700)

while True:

    success, frame = cap.read()

    if not success:
        break

    results = model.track(
        frame,
        persist=True,
        tracker="bytetrack.yaml",
        verbose=False
    )

    annotated = results[0].plot()

    x1, y1, x2, y2 = ZONE

    cv2.rectangle(
        annotated,
        (x1, y1),
        (x2, y2),
        (0,0,255),
        2
    )

    cv2.putText(
        annotated,
        "Restricted Zone",
        (x1, y1-10),
        cv2.FONT_HERSHEY_SIMPLEX,
        0.8,
        (0,0,255),
        2
    )

    cv2.imshow("Zone Monitoring", annotated)

    if cv2.waitKey(1) == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()