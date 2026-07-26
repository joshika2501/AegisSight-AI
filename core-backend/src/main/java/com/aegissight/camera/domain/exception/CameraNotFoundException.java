package com.aegissight.camera.domain.exception;

import com.aegissight.common.domain.exception.AegisException;

public class CameraNotFoundException extends AegisException {
    public CameraNotFoundException(String message) {
        super(message);
    }
}
