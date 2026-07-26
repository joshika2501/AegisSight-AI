package com.aegissight.common.infrastructure.exception;

import com.aegissight.auth.domain.exception.InvalidCredentialsException;
import com.aegissight.camera.domain.exception.CameraAlreadyExistsException;
import com.aegissight.camera.domain.exception.CameraNotFoundException;
import com.aegissight.common.domain.exception.AegisException;
import com.aegissight.common.infrastructure.dto.ErrorResponse;
import com.aegissight.common.infrastructure.dto.FieldErrorDetail;
import com.aegissight.incident.domain.exception.IncidentNotFoundException;
import com.aegissight.incident.domain.exception.InvalidStatusTransitionException;
import jakarta.validation.ConstraintViolationException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        List<FieldErrorDetail> fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorDetail(
                        error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue()
                ))
                .toList();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(buildErrorResponse(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Validation Failed",
                        "Request body has invalid fields",
                        request.getRequestURI(),
                        fieldErrors
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJson(
            HttpServletRequest request
    ) {
        return ResponseEntity
                .badRequest()
                .body(buildErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "Malformed JSON request body",
                        request.getRequestURI(),
                        List.of()
                ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException exception,
            HttpServletRequest request
    ) {
        String message = "Invalid value for parameter '" + exception.getName() + "'";
        FieldErrorDetail fieldError = new FieldErrorDetail(
                exception.getName(),
                message,
                exception.getValue()
        );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(buildErrorResponse(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Validation Failed",
                        message,
                        request.getRequestURI(),
                        List.of(fieldError)
                ));
    }

    @ExceptionHandler(CameraNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCameraNotFound(
            CameraNotFoundException exception,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildErrorResponse(
                        HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        exception.getMessage(),
                        request.getRequestURI(),
                        List.of()
                ));
    }

    @ExceptionHandler(IncidentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIncidentNotFound(
            IncidentNotFoundException exception,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildErrorResponse(
                        HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        exception.getMessage(),
                        request.getRequestURI(),
                        List.of()
                ));
    }

    @ExceptionHandler(CameraAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCameraAlreadyExists(
            CameraAlreadyExistsException exception,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(buildErrorResponse(
                        HttpStatus.CONFLICT,
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        exception.getMessage(),
                        request.getRequestURI(),
                        List.of()
                ));
    }

    @ExceptionHandler(InvalidStatusTransitionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStatusTransition(
            InvalidStatusTransitionException exception,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(buildErrorResponse(
                        HttpStatus.CONFLICT,
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        exception.getMessage(),
                        request.getRequestURI(),
                        List.of()
                ));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(
            InvalidCredentialsException exception,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(buildErrorResponse(
                        HttpStatus.UNAUTHORIZED,
                        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        exception.getMessage(),
                        request.getRequestURI(),
                        List.of()
                ));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException exception,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(buildErrorResponse(
                        HttpStatus.FORBIDDEN,
                        HttpStatus.FORBIDDEN.getReasonPhrase(),
                        "Access denied",
                        request.getRequestURI(),
                        List.of()
                ));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException exception,
            HttpServletRequest request
    ) {
        List<FieldErrorDetail> fieldErrors = exception.getConstraintViolations()
                .stream()
                .map(violation -> new FieldErrorDetail(
                        violation.getPropertyPath().toString(),
                        violation.getMessage(),
                        violation.getInvalidValue()
                ))
                .toList();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(buildErrorResponse(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Validation Failed",
                        "Request parameters have invalid values",
                        request.getRequestURI(),
                        fieldErrors
                ));
    }

    @ExceptionHandler(AegisException.class)
    public ResponseEntity<ErrorResponse> handleAegisException(
            AegisException exception,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(buildErrorResponse(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Validation Failed",
                        exception.getMessage(),
                        request.getRequestURI(),
                        List.of()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(
            Exception exception,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Internal Server Error",
                        "Unexpected error",
                        request.getRequestURI(),
                        List.of()
                ));
    }

    private ErrorResponse buildErrorResponse(
            HttpStatus status,
            String error,
            String message,
            String path,
            List<FieldErrorDetail> fieldErrors
    ) {
        return new ErrorResponse(
                Instant.now(),
                status.value(),
                error,
                message,
                path,
                "req_" + UUID.randomUUID().toString().replace("-", "").substring(0, 12),
                fieldErrors
        );
    }
}
