package com.aegissight.detection.domain.exception;

import com.aegissight.common.domain.exception.AegisException;

public class DetectionValidationException extends AegisException {
    public DetectionValidationException(String message) {
        super(message);
    }
}
