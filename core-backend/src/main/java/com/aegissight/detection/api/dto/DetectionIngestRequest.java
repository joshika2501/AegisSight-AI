package com.aegissight.detection.api.dto;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.Severity;
import java.time.Instant;

public record DetectionIngestRequest(
    String sourceId,
    EventType eventType,
    Double confidence,
    Severity severity,
    Integer peopleCount,
    Integer vehicleCount,
    Integer riskScore,
    Instant timestamp,
    String summary
) {}
