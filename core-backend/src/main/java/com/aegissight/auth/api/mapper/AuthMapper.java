package com.aegissight.auth.api.mapper;

import com.aegissight.auth.api.dto.AuthResponse;
import com.aegissight.auth.api.dto.UserDto;
import com.aegissight.auth.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthResponse toResponse(User user, String accessToken, long expiresInSeconds) {
        return new AuthResponse(
                accessToken,
                "Bearer",
                expiresInSeconds,
                toUserDto(user)
        );
    }

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getDisplayName()
        );
    }
}
