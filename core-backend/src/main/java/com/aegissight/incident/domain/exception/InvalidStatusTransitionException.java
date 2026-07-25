package com.aegissight.incident.domain.exception;

import com.aegissight.common.domain.exception.AegisException;

public class InvalidStatusTransitionException extends AegisException {
    public InvalidStatusTransitionException(String message) {
        super(message);
    }
}
