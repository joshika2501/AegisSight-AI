CREATE TABLE cameras (
    id              VARCHAR(64) PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    platform        VARCHAR(32) NOT NULL,
    location_label  VARCHAR(255) NOT NULL,
    active          BOOLEAN NOT NULL DEFAULT TRUE,
    latitude        DOUBLE PRECISION,
    longitude       DOUBLE PRECISION
);

CREATE INDEX idx_cameras_platform ON cameras (platform);
