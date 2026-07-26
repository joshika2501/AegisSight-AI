package com.aegissight.auth.domain.repository;

import com.aegissight.auth.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findById(UUID id);

    boolean existsByUsername(String username);

    User save(User user);
}