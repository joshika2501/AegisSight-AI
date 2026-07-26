package com.aegissight.alert.api.controller;

import com.aegissight.alert.api.dto.AlertResponse;
import com.aegissight.alert.application.service.AlertService;
import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.common.infrastructure.dto.PageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public PageResponse<AlertResponse> listAlerts(
            @RequestParam(required = false) AlertStatus status,
            @RequestParam(required = false) Severity severity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return alertService.listAlerts(status, severity, page, size);
    }
}
