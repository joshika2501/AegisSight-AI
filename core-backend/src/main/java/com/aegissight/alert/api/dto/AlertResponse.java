package com.aegissight.alert.api.dto;

import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import java.time.Instant;
import java.util.UUID;

public record AlertResponse(
    UUID id,
    UUID incidentId,
    String incidentCode,
    String title,
    String message,
    Severity severity,
    AlertStatus status,
    String sourceId,
    String locationLabel,
    Instant createdAt
) {}
