CREATE TABLE detections (
    id              UUID PRIMARY KEY,
    source_id       VARCHAR(64) NOT NULL,
    event_type      VARCHAR(64) NOT NULL,
    confidence      DOUBLE PRECISION NOT NULL,
    severity        VARCHAR(16) NOT NULL,
    people_count    INTEGER,
    vehicle_count   INTEGER,
    risk_score      INTEGER NOT NULL,
    detected_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    summary         TEXT,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_detections_camera FOREIGN KEY (source_id) REFERENCES cameras (id)
);

CREATE INDEX idx_detections_source_id ON detections (source_id);
CREATE INDEX idx_detections_detected_at ON detections (detected_at DESC);
