package com.aegissight.detection.domain.entity;

import com.aegissight.common.domain.model.EventType;
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
@Table(name = "detections")
public class Detection {

    @Id
    private UUID id;

    @Column(name = "source_id", nullable = false)
    private String sourceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Column(nullable = false)
    private Double confidence;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Column(name = "people_count")
    private Integer peopleCount;

    @Column(name = "vehicle_count")
    private Integer vehicleCount;

    @Column(name = "risk_score", nullable = false)
    private Integer riskScore;

    @Column(name = "detected_at", nullable = false)
    private Instant detectedAt;

    private String summary;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected Detection() {
    }

    public Detection(
            UUID id,
            String sourceId,
            EventType eventType,
            Double confidence,
            Severity severity,
            Integer peopleCount,
            Integer vehicleCount,
            Integer riskScore,
            Instant detectedAt,
            String summary,
            Instant createdAt
    ) {
        this.id = id;
        this.sourceId = sourceId;
        this.eventType = eventType;
        this.confidence = confidence;
        this.severity = severity;
        this.peopleCount = peopleCount;
        this.vehicleCount = vehicleCount;
        this.riskScore = riskScore;
        this.detectedAt = detectedAt;
        this.summary = summary;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Double getConfidence() {
        return confidence;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public Integer getVehicleCount() {
        return vehicleCount;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public Instant getDetectedAt() {
        return detectedAt;
    }

    public String getSummary() {
        return summary;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
