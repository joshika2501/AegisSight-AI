package com.aegissight.camera.api.mapper;

import com.aegissight.camera.api.dto.CameraResponse;
import com.aegissight.camera.api.dto.RegisterCameraRequest;
import com.aegissight.camera.domain.entity.Camera;
import org.springframework.stereotype.Component;

@Component
public class CameraMapper {

    public Camera toEntity(RegisterCameraRequest request) {
        return new Camera(
                request.id(),
                request.name(),
                request.platform(),
                request.locationLabel(),
                request.active(),
                request.latitude(),
                request.longitude()
        );
    }

    public CameraResponse toResponse(Camera camera) {
        return new CameraResponse(
                camera.getId(),
                camera.getName(),
                camera.getPlatform(),
                camera.getLocationLabel(),
                camera.getActive(),
                camera.getLatitude(),
                camera.getLongitude()
        );
    }
}
