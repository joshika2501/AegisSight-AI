package com.aegissight.auth.api.controller;

import com.aegissight.auth.api.dto.AuthResponse;
import com.aegissight.auth.api.dto.LoginRequest;
import com.aegissight.auth.application.usecase.LoginUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return loginUseCase.login(request);
    }
}
