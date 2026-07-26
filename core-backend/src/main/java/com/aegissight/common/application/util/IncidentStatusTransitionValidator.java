package com.aegissight.common.application.util;

import com.aegissight.common.domain.model.IncidentStatus;
import com.aegissight.incident.domain.exception.InvalidStatusTransitionException;
import java.util.Map;
import java.util.Set;

public final class IncidentStatusTransitionValidator {

    private static final Map<IncidentStatus, Set<IncidentStatus>> ALLOWED = Map.of(
            IncidentStatus.NEW, Set.of(IncidentStatus.VERIFIED, IncidentStatus.FALSE_ALERT),
            IncidentStatus.VERIFIED, Set.of(IncidentStatus.RESPONDING, IncidentStatus.FALSE_ALERT),
            IncidentStatus.RESPONDING, Set.of(IncidentStatus.RESOLVED)
    );

    private IncidentStatusTransitionValidator() {
    }

    public static void validate(IncidentStatus current, IncidentStatus target) {
        if (current == target) {
            return;
        }

        Set<IncidentStatus> allowedTargets = ALLOWED.get(current);
        if (allowedTargets == null || !allowedTargets.contains(target)) {
            throw new InvalidStatusTransitionException(
                    "Cannot transition incident from " + current + " to " + target
            );
        }
    }
}
