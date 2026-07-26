CREATE TABLE incidents (
    id              UUID PRIMARY KEY,
    incident_code   VARCHAR(32) NOT NULL UNIQUE,
    detection_id    UUID NOT NULL UNIQUE,
    source_id       VARCHAR(64) NOT NULL,
    title           VARCHAR(255) NOT NULL,
    summary         TEXT,
    event_type      VARCHAR(64) NOT NULL,
    severity        VARCHAR(16) NOT NULL,
    risk_score      INTEGER NOT NULL,
    status          VARCHAR(32) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_incidents_detection FOREIGN KEY (detection_id) REFERENCES detections (id),
    CONSTRAINT fk_incidents_camera FOREIGN KEY (source_id) REFERENCES cameras (id)
);

CREATE INDEX idx_incidents_status ON incidents (status);
CREATE INDEX idx_incidents_severity ON incidents (severity);
CREATE INDEX idx_incidents_event_type ON incidents (event_type);
CREATE INDEX idx_incidents_source_id ON incidents (source_id);
CREATE INDEX idx_incidents_created_at ON incidents (created_at DESC);
