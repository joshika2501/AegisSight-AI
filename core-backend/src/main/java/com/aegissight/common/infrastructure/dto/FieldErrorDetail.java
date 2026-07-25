package com.aegissight.common.infrastructure.dto;

public record FieldErrorDetail(
    String field,
    String message,
    Object rejectedValue
) {}
