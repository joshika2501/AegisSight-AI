package com.aegissight.incident.application.service;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.IncidentStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.common.infrastructure.dto.PageResponse;
import com.aegissight.incident.api.dto.CriticalIncidentResponse;
import com.aegissight.incident.api.dto.IncidentDetailResponse;
import com.aegissight.incident.api.dto.IncidentResponse;
import com.aegissight.incident.api.dto.UpdateIncidentStatusRequest;
import com.aegissight.incident.api.dto.UpdateIncidentStatusResponse;
import java.util.List;
import java.util.UUID;

public interface IncidentService {

    PageResponse<IncidentResponse> listIncidents(
            IncidentStatus status,
            Severity severity,
            EventType eventType,
            String sourceId,
            int page,
            int size
    );

    IncidentDetailResponse getIncident(UUID id);

    List<CriticalIncidentResponse> listCriticalIncidents();

    UpdateIncidentStatusResponse updateStatus(UUID id, UpdateIncidentStatusRequest request);
}
