package com.aegissight.camera.application.service;

import com.aegissight.camera.api.dto.CameraResponse;
import com.aegissight.camera.api.dto.RegisterCameraRequest;
import com.aegissight.common.domain.model.CameraPlatform;
import java.util.List;

public interface CameraService {

    CameraResponse register(RegisterCameraRequest request);

    List<CameraResponse> list(CameraPlatform platform);
}
