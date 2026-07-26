package com.aegissight.incident.api.controller;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.IncidentStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.common.infrastructure.dto.PageResponse;
import com.aegissight.incident.api.dto.CriticalIncidentResponse;
import com.aegissight.incident.api.dto.IncidentDetailResponse;
import com.aegissight.incident.api.dto.IncidentResponse;
import com.aegissight.incident.api.dto.UpdateIncidentStatusRequest;
import com.aegissight.incident.api.dto.UpdateIncidentStatusResponse;
import com.aegissight.incident.application.service.IncidentService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public PageResponse<IncidentResponse> listIncidents(
            @RequestParam(required = false) IncidentStatus status,
            @RequestParam(required = false) Severity severity,
            @RequestParam(required = false) EventType eventType,
            @RequestParam(required = false) String sourceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return incidentService.listIncidents(status, severity, eventType, sourceId, page, size);
    }

    @GetMapping("/critical")
    public List<CriticalIncidentResponse> listCriticalIncidents() {
        return incidentService.listCriticalIncidents();
    }

    @GetMapping("/{id}")
    public IncidentDetailResponse getIncident(@PathVariable UUID id) {
        return incidentService.getIncident(id);
    }

    @PutMapping("/{id}/status")
    public UpdateIncidentStatusResponse updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateIncidentStatusRequest request
    ) {
        return incidentService.updateStatus(id, request);
    }
}
