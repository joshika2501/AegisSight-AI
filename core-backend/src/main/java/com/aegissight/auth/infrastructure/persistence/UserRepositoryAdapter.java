package com.aegissight.auth.infrastructure.persistence;

import com.aegissight.auth.domain.entity.User;
import com.aegissight.auth.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public class UserRepositoryAdapter
        implements UserRepository {


    private final UserJpaRepository repository;


    public UserRepositoryAdapter(UserJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }


    @Override
    public User save(User user) {
        return repository.save(user);
    }


    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id);
    }


    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}