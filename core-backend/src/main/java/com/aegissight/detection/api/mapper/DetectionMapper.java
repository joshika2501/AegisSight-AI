package com.aegissight.detection.api.mapper;

import com.aegissight.detection.api.dto.DetectionIngestRequest;
import com.aegissight.detection.api.dto.DetectionIngestResponse;
import com.aegissight.detection.domain.entity.Detection;
import com.aegissight.incident.domain.entity.Incident;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class DetectionMapper {

    public Detection toEntity(DetectionIngestRequest request, UUID id, Instant createdAt) {
        return new Detection(
                id,
                request.sourceId(),
                request.eventType(),
                request.confidence(),
                request.severity(),
                request.peopleCount(),
                request.vehicleCount(),
                request.riskScore(),
                request.timestamp(),
                request.summary(),
                createdAt
        );
    }

    public DetectionIngestResponse toIngestResponse(
            Detection detection,
            Incident incident,
            boolean alertCreated,
            UUID alertId
    ) {
        return new DetectionIngestResponse(
                detection.getId(),
                incident.getId(),
                incident.getIncidentCode(),
                incident.getStatus(),
                alertCreated,
                alertId
        );
    }
}
