package com.aegissight.auth.domain.entity;

import java.time.Instant;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String passwordHash;
    private String displayName;
    private Instant createdAt;
}
