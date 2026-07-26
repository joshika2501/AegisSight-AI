package com.aegissight.health.api.controller;

import com.aegissight.health.api.dto.HealthResponse;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final String serviceName;
    private final String version;

    public HealthController(
            @Value("${aegissight.app.service-name}") String serviceName,
            @Value("${aegissight.app.version}") String version
    ) {
        this.serviceName = serviceName;
        this.version = version;
    }

    @GetMapping
    public HealthResponse health() {
        return new HealthResponse(
                "UP",
                serviceName,
                version,
                Instant.now()
        );
    }
}
