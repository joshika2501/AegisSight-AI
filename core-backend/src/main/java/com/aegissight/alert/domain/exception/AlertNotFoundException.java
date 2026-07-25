package com.aegissight.alert.domain.exception;

import com.aegissight.common.domain.exception.AegisException;

public class AlertNotFoundException extends AegisException {
    public AlertNotFoundException(String message) {
        super(message);
    }
}
