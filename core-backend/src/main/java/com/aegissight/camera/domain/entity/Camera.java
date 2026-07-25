package com.aegissight.camera.domain.entity;

import com.aegissight.common.domain.model.CameraPlatform;

public class Camera {
    private String id;
    private String name;
    private CameraPlatform platform;
    private String locationLabel;
    private Boolean active;
    private Double latitude;
    private Double longitude;
}
