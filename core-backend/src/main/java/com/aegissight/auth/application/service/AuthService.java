package com.aegissight.auth.application.service;

import com.aegissight.auth.domain.entity.User;
import com.aegissight.auth.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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