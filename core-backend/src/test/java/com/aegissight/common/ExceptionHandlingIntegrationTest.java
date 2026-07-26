package com.aegissight.common;

import com.aegissight.IntegrationTestSupport;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExceptionHandlingIntegrationTest extends IntegrationTestSupport {

    @Test
    void malformedJsonReturnsBadRequest() throws Exception {
        String token = loginAndGetToken();

        mockMvc.perform(post("/api/cameras")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("{"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void invalidRequestBodyReturnsValidationError() throws Exception {
        String token = loginAndGetToken();

        mockMvc.perform(post("/api/cameras")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("""
                                {
                                  "id": "",
                                  "name": "",
                                  "platform": "FIXED_CAMERA",
                                  "locationLabel": "",
                                  "active": true,
                                  "latitude": 200,
                                  "longitude": 85.8245
                                }
                                """))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.status").value(422));
    }

    @Test
    void invalidPaginationReturnsValidationError() throws Exception {
        String token = loginAndGetToken();

        mockMvc.perform(get("/api/incidents")
                        .header("Authorization", "Bearer " + token)
                        .param("size", "101"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.status").value(422));
    }
}
