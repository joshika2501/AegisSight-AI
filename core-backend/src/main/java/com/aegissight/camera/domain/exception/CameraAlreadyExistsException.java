package com.aegissight.camera.domain.exception;

import com.aegissight.common.domain.exception.AegisException;

public class CameraAlreadyExistsException extends AegisException {
    public CameraAlreadyExistsException(String message) {
        super(message);
    }
}
