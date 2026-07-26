package com.aegissight.camera.domain.repository;

import com.aegissight.camera.domain.entity.Camera;
import com.aegissight.common.domain.model.CameraPlatform;
import java.util.List;
import java.util.Optional;

public interface CameraRepository {

    Camera save(Camera camera);

    Optional<Camera> findById(String id);

    boolean existsById(String id);

    List<Camera> findAll(CameraPlatform platform);
}
