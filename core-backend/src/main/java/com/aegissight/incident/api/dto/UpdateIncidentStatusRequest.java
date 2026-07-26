package com.aegissight.incident.api.dto;

import com.aegissight.common.domain.model.IncidentStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateIncidentStatusRequest(
    @NotNull(message = "status is required")
    IncidentStatus status,

    String note
) {}
