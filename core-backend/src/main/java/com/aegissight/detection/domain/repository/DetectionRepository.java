package com.aegissight.detection.domain.repository;

import com.aegissight.detection.domain.entity.Detection;
import java.util.Optional;
import java.util.UUID;

public interface DetectionRepository {

    Detection save(Detection detection);

    Optional<Detection> findById(UUID id);
}
