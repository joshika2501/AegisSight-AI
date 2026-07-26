package com.aegissight.alert.infrastructure.persistence;

import com.aegissight.alert.domain.entity.Alert;
import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlertJpaRepository extends JpaRepository<Alert, UUID>, JpaSpecificationExecutor<Alert> {

    List<Alert> findByIncidentId(UUID incidentId);

    Page<Alert> findByStatus(AlertStatus status, Pageable pageable);

    Page<Alert> findBySeverity(Severity severity, Pageable pageable);

    Page<Alert> findByStatusAndSeverity(AlertStatus status, Severity severity, Pageable pageable);
}
