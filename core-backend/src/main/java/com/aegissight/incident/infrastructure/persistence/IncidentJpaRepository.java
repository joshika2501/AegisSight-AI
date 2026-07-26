package com.aegissight.incident.infrastructure.persistence;

import com.aegissight.common.domain.model.IncidentStatus;
import com.aegissight.common.domain.model.Severity;
import com.aegissight.incident.domain.entity.Incident;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IncidentJpaRepository extends JpaRepository<Incident, UUID>, JpaSpecificationExecutor<Incident> {

    long countByIncidentCodeStartingWith(String prefix);

    @Query("""
            SELECT i FROM Incident i
            WHERE i.status NOT IN :terminalStatuses
            AND (i.severity = :criticalSeverity OR i.riskScore >= :minRiskScore)
            ORDER BY i.createdAt DESC
            """)
    List<Incident> findCriticalActive(
            @Param("terminalStatuses") List<IncidentStatus> terminalStatuses,
            @Param("criticalSeverity") Severity criticalSeverity,
            @Param("minRiskScore") int minRiskScore
    );
}
