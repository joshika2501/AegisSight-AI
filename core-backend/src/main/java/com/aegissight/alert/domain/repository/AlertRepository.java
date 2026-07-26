package com.aegissight.alert.domain.repository;

import com.aegissight.alert.domain.entity.Alert;
import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertRepository {

    Alert save(Alert alert);

    Optional<Alert> findById(UUID id);

    List<Alert> findByIncidentId(UUID incidentId);

    Page<Alert> findAll(AlertStatus status, Severity severity, Pageable pageable);
}
