package com.aegissight.auth.application.usecase;

import com.aegissight.auth.api.dto.AuthResponse;
import com.aegissight.auth.api.dto.LoginRequest;

public interface LoginUseCase {
    AuthResponse login(LoginRequest request);
}
