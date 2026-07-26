package com.aegissight.camera.infrastructure.persistence;

import com.aegissight.camera.domain.entity.Camera;
import com.aegissight.camera.domain.repository.CameraRepository;
import com.aegissight.common.domain.model.CameraPlatform;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CameraRepositoryAdapter implements CameraRepository {

    private final CameraJpaRepository repository;

    public CameraRepositoryAdapter(CameraJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Camera save(Camera camera) {
        return repository.save(camera);
    }

    @Override
    public Optional<Camera> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public List<Camera> findAll(CameraPlatform platform) {
        if (platform == null) {
            return repository.findAll();
        }
        return repository.findByPlatform(platform);
    }
}
