package com.aegissight.alert.application.service;

import com.aegissight.alert.api.dto.AlertResponse;
import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.common.infrastructure.dto.PageResponse;

public interface AlertService {

    PageResponse<AlertResponse> listAlerts(AlertStatus status, Severity severity, int page, int size);
}
