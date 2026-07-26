package com.aegissight.alert.application.service;

import com.aegissight.alert.api.dto.AlertResponse;
import com.aegissight.alert.api.mapper.AlertMapper;
import com.aegissight.alert.domain.entity.Alert;
import com.aegissight.alert.domain.repository.AlertRepository;
import com.aegissight.camera.domain.entity.Camera;
import com.aegissight.camera.domain.repository.CameraRepository;
import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.common.infrastructure.dto.PageResponse;
import com.aegissight.incident.domain.entity.Incident;
import com.aegissight.incident.domain.repository.IncidentRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlertServiceImpl implements AlertService {

    private static final int MAX_PAGE_SIZE = 100;

    private final AlertRepository alertRepository;
    private final IncidentRepository incidentRepository;
    private final CameraRepository cameraRepository;
    private final AlertMapper alertMapper;

    public AlertServiceImpl(
            AlertRepository alertRepository,
            IncidentRepository incidentRepository,
            CameraRepository cameraRepository,
            AlertMapper alertMapper
    ) {
        this.alertRepository = alertRepository;
        this.incidentRepository = incidentRepository;
        this.cameraRepository = cameraRepository;
        this.alertMapper = alertMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<AlertResponse> listAlerts(AlertStatus status, Severity severity, int page, int size) {
        int pageSize = Math.min(Math.max(size, 1), MAX_PAGE_SIZE);
        PageRequest pageRequest = PageRequest.of(Math.max(page, 0), pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Alert> alertPage = alertRepository.findAll(status, severity, pageRequest);

        List<AlertResponse> items = alertPage.getContent().stream()
                .map(this::toAlertResponse)
                .toList();

        return new PageResponse<>(items, toPageDetails(alertPage));
    }

    private AlertResponse toAlertResponse(Alert alert) {
        Incident incident = incidentRepository.findById(alert.getIncidentId()).orElse(null);
        Camera camera = incident != null
                ? cameraRepository.findById(incident.getSourceId()).orElse(null)
                : null;

        return alertMapper.toResponse(alert, incident, camera);
    }

    private PageResponse.PageDetails toPageDetails(Page<Alert> page) {
        return new PageResponse.PageDetails(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext()
        );
    }
}
