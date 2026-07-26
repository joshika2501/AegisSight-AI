package com.aegissight.incident.infrastructure.persistence;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.IncidentStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.incident.domain.entity.Incident;
import com.aegissight.incident.domain.repository.IncidentRepository;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class IncidentRepositoryAdapter implements IncidentRepository {

    private final IncidentJpaRepository repository;

    public IncidentRepositoryAdapter(IncidentJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Incident save(Incident incident) {
        return repository.save(incident);
    }

    @Override
    public Optional<Incident> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public long countByIncidentCodeStartingWith(String prefix) {
        return repository.countByIncidentCodeStartingWith(prefix);
    }

    @Override
    public Page<Incident> findAll(
            IncidentStatus status,
            Severity severity,
            EventType eventType,
            String sourceId,
            Pageable pageable
    ) {
        return repository.findAll(buildSpecification(status, severity, eventType, sourceId), pageable);
    }

    @Override
    public List<Incident> findCriticalActive() {
        return repository.findCriticalActive(
                List.of(IncidentStatus.RESOLVED, IncidentStatus.FALSE_ALERT),
                Severity.CRITICAL,
                90
        );
    }

    private Specification<Incident> buildSpecification(
            IncidentStatus status,
            Severity severity,
            EventType eventType,
            String sourceId
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (severity != null) {
                predicates.add(criteriaBuilder.equal(root.get("severity"), severity));
            }
            if (eventType != null) {
                predicates.add(criteriaBuilder.equal(root.get("eventType"), eventType));
            }
            if (sourceId != null) {
                predicates.add(criteriaBuilder.equal(root.get("sourceId"), sourceId));
            }

            if (predicates.isEmpty()) {
                return null;
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
