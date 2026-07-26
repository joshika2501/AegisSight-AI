package com.aegissight.detection.application.service;

import com.aegissight.alert.domain.entity.Alert;
import com.aegissight.alert.domain.repository.AlertRepository;
import com.aegissight.camera.domain.entity.Camera;
import com.aegissight.camera.domain.exception.CameraNotFoundException;
import com.aegissight.camera.domain.repository.CameraRepository;
import com.aegissight.common.application.util.EventTypeFormatter;
import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.IncidentStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.detection.api.dto.DetectionIngestRequest;
import com.aegissight.detection.api.dto.DetectionIngestResponse;
import com.aegissight.detection.api.mapper.DetectionMapper;
import com.aegissight.detection.domain.entity.Detection;
import com.aegissight.detection.domain.repository.DetectionRepository;
import com.aegissight.incident.domain.entity.Incident;
import com.aegissight.incident.domain.repository.IncidentRepository;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetectionServiceImpl implements DetectionService {

    private static final DateTimeFormatter INCIDENT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneOffset.UTC);

    private final CameraRepository cameraRepository;
    private final DetectionRepository detectionRepository;
    private final IncidentRepository incidentRepository;
    private final AlertRepository alertRepository;
    private final DetectionMapper detectionMapper;

    public DetectionServiceImpl(
            CameraRepository cameraRepository,
            DetectionRepository detectionRepository,
            IncidentRepository incidentRepository,
            AlertRepository alertRepository,
            DetectionMapper detectionMapper
    ) {
        this.cameraRepository = cameraRepository;
        this.detectionRepository = detectionRepository;
        this.incidentRepository = incidentRepository;
        this.alertRepository = alertRepository;
        this.detectionMapper = detectionMapper;
    }

    @Override
    @Transactional
    public DetectionIngestResponse ingest(DetectionIngestRequest request) {
        Camera camera = cameraRepository.findById(request.sourceId())
                .orElseThrow(() -> new CameraNotFoundException(
                        "No camera/source found for sourceId: " + request.sourceId()
                ));

        Instant now = Instant.now();
        UUID detectionId = UUID.randomUUID();

        Detection detection = detectionMapper.toEntity(request, detectionId, now);
        detectionRepository.save(detection);

        String incidentCode = generateIncidentCode(now);
        String title = EventTypeFormatter.toTitle(request.eventType());

        Incident incident = new Incident(
                UUID.randomUUID(),
                incidentCode,
                detectionId,
                request.sourceId(),
                title,
                request.summary(),
                request.eventType(),
                request.severity(),
                request.riskScore(),
                IncidentStatus.NEW,
                now,
                now
        );
        incidentRepository.save(incident);

        UUID alertId = null;
        boolean alertCreated = false;

        if (shouldCreateAlert(request.severity(), request.riskScore())) {
            Alert alert = new Alert(
                    UUID.randomUUID(),
                    incident.getId(),
                    EventTypeFormatter.toAlertTitle(request.severity()),
                    buildAlertMessage(request.summary(), title, camera),
                    request.severity(),
                    AlertStatus.OPEN,
                    now
            );
            alertRepository.save(alert);
            alertCreated = true;
            alertId = alert.getId();
        }

        return detectionMapper.toIngestResponse(detection, incident, alertCreated, alertId);
    }

    private String generateIncidentCode(Instant timestamp) {
        String datePart = INCIDENT_DATE_FORMAT.format(timestamp);
        String prefix = "INC-" + datePart + "-";
        long count = incidentRepository.countByIncidentCodeStartingWith(prefix);
        return prefix + String.format("%04d", count + 1);
    }

    private boolean shouldCreateAlert(Severity severity, Integer riskScore) {
        return severity == Severity.HIGH
                || severity == Severity.CRITICAL
                || riskScore >= 80;
    }

    private String buildAlertMessage(String summary, String title, Camera camera) {
        if (summary != null && !summary.isBlank()) {
            return summary;
        }
        if (camera != null && camera.getLocationLabel() != null) {
            return title + " near " + camera.getLocationLabel() + ".";
        }
        return title + ".";
    }
}
