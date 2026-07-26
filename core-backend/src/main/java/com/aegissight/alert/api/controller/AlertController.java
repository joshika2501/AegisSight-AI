package com.aegissight.alert.api.controller;

import com.aegissight.alert.api.dto.AlertResponse;
import com.aegissight.alert.application.service.AlertService;
import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.common.infrastructure.dto.PageResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
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
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "page must be 0 or greater") int page,
            @RequestParam(defaultValue = "20") @Min(value = 1, message = "size must be between 1 and 100") @Max(value = 100, message = "size must be between 1 and 100") int size
    ) {
        return alertService.listAlerts(status, severity, page, size);
    }
}
