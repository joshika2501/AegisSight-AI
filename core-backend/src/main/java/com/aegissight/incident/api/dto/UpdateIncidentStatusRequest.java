package com.aegissight.incident.api.dto;

import com.aegissight.common.domain.model.IncidentStatus;

public record UpdateIncidentStatusRequest(
    IncidentStatus status,
    String note
) {}
