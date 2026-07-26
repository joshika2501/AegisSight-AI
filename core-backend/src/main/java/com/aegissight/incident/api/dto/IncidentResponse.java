package com.aegissight.incident.api.dto;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.IncidentStatus;
import com.aegissight.common.domain.model.Severity;
import java.time.Instant;
import java.util.UUID;

public record IncidentResponse(
    UUID id,
    String incidentCode,
    String title,
    EventType eventType,
    Severity severity,
    IncidentStatus status,
    Integer riskScore,
    String sourceId,
    String cameraName,
    String locationLabel,
    Instant createdAt,
    Instant updatedAt
) {}
