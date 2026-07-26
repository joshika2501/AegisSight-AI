package com.aegissight;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
abstract class IntegrationTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected String loginAndGetToken() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content("""
                                {
                                  "username": "operator@aegissight.local",
                                  "password": "password123"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode response = objectMapper.readTree(result.getResponse().getContentAsString());
        return response.get("accessToken").asText();
    }

    protected void registerCamera(String token, String cameraId) throws Exception {
        mockMvc.perform(post("/api/cameras")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("""
                                {
                                  "id": "%s",
                                  "name": "Main Gate Camera",
                                  "platform": "FIXED_CAMERA",
                                  "locationLabel": "Main Gate",
                                  "active": true,
                                  "latitude": 20.2961,
                                  "longitude": 85.8245
                                }
                                """.formatted(cameraId)))
                .andExpect(status().isCreated());
    }

    protected JsonNode createIncidentFromDetection(String token, String cameraId, String severity, int riskScore) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/detections")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("""
                                {
                                  "sourceId": "%s",
                                  "eventType": "PHYSICAL_DISTURBANCE",
                                  "confidence": 0.94,
                                  "severity": "%s",
                                  "peopleCount": 28,
                                  "vehicleCount": 2,
                                  "riskScore": %d,
                                  "timestamp": "2026-07-23T10:15:30Z",
                                  "summary": "Possible physical disturbance detected near the main gate."
                                }
                                """.formatted(cameraId, severity, riskScore)))
                .andExpect(status().isCreated())
                .andReturn();

        return objectMapper.readTree(result.getResponse().getContentAsString());
    }
}
