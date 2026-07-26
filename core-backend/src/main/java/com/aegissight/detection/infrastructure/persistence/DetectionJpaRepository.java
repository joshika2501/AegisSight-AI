package com.aegissight.detection.infrastructure.persistence;

import com.aegissight.detection.domain.entity.Detection;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetectionJpaRepository extends JpaRepository<Detection, UUID> {
}
