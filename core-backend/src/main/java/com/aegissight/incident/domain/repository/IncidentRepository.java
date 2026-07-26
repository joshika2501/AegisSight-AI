package com.aegissight.incident.domain.repository;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.IncidentStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.incident.domain.entity.Incident;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidentRepository {

    Incident save(Incident incident);

    Optional<Incident> findById(UUID id);

    long countByIncidentCodeStartingWith(String prefix);

    Page<Incident> findAll(
            IncidentStatus status,
            Severity severity,
            EventType eventType,
            String sourceId,
            Pageable pageable
    );

    List<Incident> findCriticalActive();
}
