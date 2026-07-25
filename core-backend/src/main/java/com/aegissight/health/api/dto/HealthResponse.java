package com.aegissight.health.api.dto;

import java.time.Instant;

public record HealthResponse(
        String status,
        Instant timestamp
) {}