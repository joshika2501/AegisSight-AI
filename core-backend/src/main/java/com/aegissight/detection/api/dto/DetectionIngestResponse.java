package com.aegissight.detection.api.dto;

import com.aegissight.common.domain.model.IncidentStatus;
import java.util.UUID;

public record DetectionIngestResponse(
    UUID detectionId,
    UUID incidentId,
    String incidentCode,
    IncidentStatus incidentStatus,
    boolean alertCreated,
    UUID alertId
) {}
