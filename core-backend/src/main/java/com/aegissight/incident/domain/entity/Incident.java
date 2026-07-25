package com.aegissight.incident.domain.entity;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.IncidentStatus;
import com.aegissight.common.domain.model.Severity;
import java.time.Instant;
import java.util.UUID;

public class Incident {
    private UUID id;
    private String incidentCode;
    private UUID detectionId;
    private String sourceId;
    private String title;
    private String summary;
    private EventType eventType;
    private Severity severity;
    private Integer riskScore;
    private IncidentStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
