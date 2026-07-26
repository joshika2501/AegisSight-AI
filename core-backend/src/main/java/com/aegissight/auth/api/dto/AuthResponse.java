package com.aegissight.auth.api.dto;

public record AuthResponse(
    String accessToken,
    String tokenType,
    long expiresInSeconds,
    UserDto user
) {}
