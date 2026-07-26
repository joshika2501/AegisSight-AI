package com.aegissight.camera.domain.entity;

import com.aegissight.common.domain.model.CameraPlatform;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cameras")
public class Camera {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CameraPlatform platform;

    @Column(name = "location_label", nullable = false)
    private String locationLabel;

    @Column(nullable = false)
    private Boolean active;

    private Double latitude;

    private Double longitude;

    protected Camera() {
    }

    public Camera(
            String id,
            String name,
            CameraPlatform platform,
            String locationLabel,
            Boolean active,
            Double latitude,
            Double longitude
    ) {
        this.id = id;
        this.name = name;
        this.platform = platform;
        this.locationLabel = locationLabel;
        this.active = active;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CameraPlatform getPlatform() {
        return platform;
    }

    public String getLocationLabel() {
        return locationLabel;
    }

    public Boolean getActive() {
        return active;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
