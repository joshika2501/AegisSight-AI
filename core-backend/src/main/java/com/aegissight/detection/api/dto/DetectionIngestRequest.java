package com.aegissight.detection.api.dto;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.Severity;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record DetectionIngestRequest(
    @NotBlank(message = "sourceId is required")
    String sourceId,

    @NotNull(message = "eventType is required")
    EventType eventType,

    @NotNull(message = "confidence is required")
    @DecimalMin(value = "0.0", message = "must be between 0.0 and 1.0")
    @DecimalMax(value = "1.0", message = "must be between 0.0 and 1.0")
    Double confidence,

    @NotNull(message = "severity is required")
    Severity severity,

    @Min(value = 0, message = "must be 0 or greater")
    Integer peopleCount,

    @Min(value = 0, message = "must be 0 or greater")
    Integer vehicleCount,

    @NotNull(message = "riskScore is required")
    @Min(value = 0, message = "must be between 0 and 100")
    @Max(value = 100, message = "must be between 0 and 100")
    Integer riskScore,

    @NotNull(message = "timestamp is required")
    Instant timestamp,

    String summary
) {}
