package com.aegissight.auth.application.service;

import com.aegissight.auth.api.dto.AuthResponse;
import com.aegissight.auth.api.dto.LoginRequest;
import com.aegissight.auth.api.mapper.AuthMapper;
import com.aegissight.auth.domain.entity.User;
import com.aegissight.auth.domain.exception.InvalidCredentialsException;
import com.aegissight.auth.domain.repository.UserRepository;
import com.aegissight.auth.application.usecase.LoginUseCase;
import com.aegissight.auth.infrastructure.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthMapper authMapper;


    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider,
            AuthMapper authMapper
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authMapper = authMapper;
    }


    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.username())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String accessToken = jwtTokenProvider.generateToken(user);

        return authMapper.toResponse(
                user,
                accessToken,
                jwtTokenProvider.getExpirationSeconds()
        );
    }


    public User createUser(User user) {

        if(userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException(
                    "Username already exists"
            );
        }

        return userRepository.save(user);
    }


    public User getByUsername(String username){

        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );
    }
}
