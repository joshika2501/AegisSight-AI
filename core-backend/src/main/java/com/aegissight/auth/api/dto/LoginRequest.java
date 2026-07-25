package com.aegissight.auth.api.dto;

public record LoginRequest(
    String username,
    String password
) {}
