package com.aegissight.incident.domain.exception;

import com.aegissight.common.domain.exception.AegisException;

public class IncidentNotFoundException extends AegisException {
    public IncidentNotFoundException(String message) {
        super(message);
    }
}
