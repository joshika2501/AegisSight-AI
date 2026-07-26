package com.aegissight.auth;

import com.aegissight.IntegrationTestSupport;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthenticationIntegrationTest extends IntegrationTestSupport {

    @Test
    void loginReturnsJwtForValidCredentials() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content("""
                                {
                                  "username": "operator@aegissight.local",
                                  "password": "password123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", not(blankOrNullString())))
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.expiresInSeconds").value(86400))
                .andExpect(jsonPath("$.user.username").value("operator@aegissight.local"))
                .andExpect(jsonPath("$.user.displayName").value("Control Room Operator"));
    }

    @Test
    void loginRejectsWrongPassword() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content("""
                                {
                                  "username": "operator@aegissight.local",
                                  "password": "wrong-password"
                                }
                                """))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401));
    }

    @Test
    void protectedEndpointRejectsInvalidToken() throws Exception {
        mockMvc.perform(get("/api/incidents")
                        .header("Authorization", "Bearer invalid-token"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401));
    }
}
