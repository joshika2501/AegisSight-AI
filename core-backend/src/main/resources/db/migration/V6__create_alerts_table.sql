CREATE TABLE alerts (
    id              UUID PRIMARY KEY,
    incident_id     UUID NOT NULL,
    title           VARCHAR(255) NOT NULL,
    message         TEXT NOT NULL,
    severity        VARCHAR(16) NOT NULL,
    status          VARCHAR(32) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_alerts_incident FOREIGN KEY (incident_id) REFERENCES incidents (id)
);

CREATE INDEX idx_alerts_incident_id ON alerts (incident_id);
CREATE INDEX idx_alerts_status ON alerts (status);
CREATE INDEX idx_alerts_severity ON alerts (severity);
CREATE INDEX idx_alerts_created_at ON alerts (created_at DESC);
