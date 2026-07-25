package com.aegissight.alert.domain.entity;

import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import java.time.Instant;
import java.util.UUID;

public class Alert {
    private UUID id;
    private UUID incidentId;
    private String title;
    private String message;
    private Severity severity;
    private AlertStatus status;
    private Instant createdAt;
}
