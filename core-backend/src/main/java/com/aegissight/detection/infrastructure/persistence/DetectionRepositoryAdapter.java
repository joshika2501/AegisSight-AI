package com.aegissight.detection.infrastructure.persistence;

import com.aegissight.detection.domain.entity.Detection;
import com.aegissight.detection.domain.repository.DetectionRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class DetectionRepositoryAdapter implements DetectionRepository {

    private final DetectionJpaRepository repository;

    public DetectionRepositoryAdapter(DetectionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Detection save(Detection detection) {
        return repository.save(detection);
    }

    @Override
    public Optional<Detection> findById(UUID id) {
        return repository.findById(id);
    }
}
