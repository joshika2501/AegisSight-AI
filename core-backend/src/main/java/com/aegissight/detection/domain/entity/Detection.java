package com.aegissight.detection.domain.entity;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.Severity;
import java.time.Instant;
import java.util.UUID;

public class Detection {
    private UUID id;
    private String sourceId;
    private EventType eventType;
    private Double confidence;
    private Severity severity;
    private Integer peopleCount;
    private Integer riskScore;
    private Instant detectedAt;
    private String summary;
    private Instant createdAt;
}
