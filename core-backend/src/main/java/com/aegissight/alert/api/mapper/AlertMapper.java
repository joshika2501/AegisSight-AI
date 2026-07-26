package com.aegissight.alert.api.mapper;

import com.aegissight.alert.api.dto.AlertResponse;
import com.aegissight.alert.domain.entity.Alert;
import com.aegissight.camera.domain.entity.Camera;
import com.aegissight.incident.domain.entity.Incident;
import org.springframework.stereotype.Component;

@Component
public class AlertMapper {

    public AlertResponse toResponse(Alert alert, Incident incident, Camera camera) {
        return new AlertResponse(
                alert.getId(),
                alert.getIncidentId(),
                incident != null ? incident.getIncidentCode() : null,
                alert.getTitle(),
                alert.getMessage(),
                alert.getSeverity(),
                alert.getStatus(),
                incident != null ? incident.getSourceId() : null,
                camera != null ? camera.getLocationLabel() : null,
                alert.getCreatedAt()
        );
    }
}
