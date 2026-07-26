package com.aegissight.alert.domain.entity;

import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    private UUID id;

    @Column(name = "incident_id", nullable = false)
    private UUID incidentId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected Alert() {
    }

    public Alert(
            UUID id,
            UUID incidentId,
            String title,
            String message,
            Severity severity,
            AlertStatus status,
            Instant createdAt
    ) {
        this.id = id;
        this.incidentId = incidentId;
        this.title = title;
        this.message = message;
        this.severity = severity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getIncidentId() {
        return incidentId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Severity getSeverity() {
        return severity;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }
}
