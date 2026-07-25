package com.aegissight.camera.api.dto;

import com.aegissight.common.domain.model.CameraPlatform;

public record RegisterCameraRequest(
    String id,
    String name,
    CameraPlatform platform,
    String locationLabel,
    Boolean active,
    Double latitude,
    Double longitude
) {}
