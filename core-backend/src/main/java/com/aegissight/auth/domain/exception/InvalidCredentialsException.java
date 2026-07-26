package com.aegissight.auth.domain.exception;

import com.aegissight.common.domain.exception.AegisException;

public class InvalidCredentialsException extends AegisException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
