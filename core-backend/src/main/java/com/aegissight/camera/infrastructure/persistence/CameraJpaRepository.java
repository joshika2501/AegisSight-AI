package com.aegissight.camera.infrastructure.persistence;

import com.aegissight.camera.domain.entity.Camera;
import com.aegissight.common.domain.model.CameraPlatform;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraJpaRepository extends JpaRepository<Camera, String> {

    List<Camera> findByPlatform(CameraPlatform platform);
}
