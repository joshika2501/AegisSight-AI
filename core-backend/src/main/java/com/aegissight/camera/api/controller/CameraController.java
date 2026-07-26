package com.aegissight.camera.api.controller;

import com.aegissight.camera.api.dto.CameraResponse;
import com.aegissight.camera.api.dto.RegisterCameraRequest;
import com.aegissight.camera.application.service.CameraService;
import com.aegissight.common.domain.model.CameraPlatform;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cameras")
public class CameraController {

    private final CameraService cameraService;

    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CameraResponse register(@Valid @RequestBody RegisterCameraRequest request) {
        return cameraService.register(request);
    }

    @GetMapping
    public List<CameraResponse> list(@RequestParam(required = false) CameraPlatform platform) {
        return cameraService.list(platform);
    }
}
