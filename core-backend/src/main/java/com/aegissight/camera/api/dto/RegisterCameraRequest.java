package com.aegissight.camera.api.dto;

import com.aegissight.common.domain.model.CameraPlatform;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterCameraRequest(
    @NotBlank(message = "id is required")
    String id,

    @NotBlank(message = "name is required")
    String name,

    @NotNull(message = "platform is required")
    CameraPlatform platform,

    @NotBlank(message = "locationLabel is required")
    String locationLabel,

    @NotNull(message = "active is required")
    Boolean active,

    @Min(value = -90, message = "latitude must be between -90 and 90")
    @Max(value = 90, message = "latitude must be between -90 and 90")
    Double latitude,

    @Min(value = -180, message = "longitude must be between -180 and 180")
    @Max(value = 180, message = "longitude must be between -180 and 180")
    Double longitude
) {}
