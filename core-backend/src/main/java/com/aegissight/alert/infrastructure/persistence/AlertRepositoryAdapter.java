package com.aegissight.alert.infrastructure.persistence;

import com.aegissight.alert.domain.entity.Alert;
import com.aegissight.alert.domain.repository.AlertRepository;
import com.aegissight.common.domain.model.AlertStatus;
import com.aegissight.common.domain.model.Severity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AlertRepositoryAdapter implements AlertRepository {

    private final AlertJpaRepository repository;

    public AlertRepositoryAdapter(AlertJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Alert save(Alert alert) {
        return repository.save(alert);
    }

    @Override
    public Optional<Alert> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Alert> findByIncidentId(UUID incidentId) {
        return repository.findByIncidentId(incidentId);
    }

    @Override
    public Page<Alert> findAll(AlertStatus status, Severity severity, Pageable pageable) {
        if (status != null && severity != null) {
            return repository.findByStatusAndSeverity(status, severity, pageable);
        }
        if (status != null) {
            return repository.findByStatus(status, pageable);
        }
        if (severity != null) {
            return repository.findBySeverity(severity, pageable);
        }
        return repository.findAll(pageable);
    }
}
