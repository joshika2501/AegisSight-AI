package com.aegissight.incident;

import com.aegissight.IntegrationTestSupport;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IncidentLifecycleIntegrationTest extends IntegrationTestSupport {

    @Test
    void incidentAcceptsValidLifecycleTransition() throws Exception {
        String token = loginAndGetToken();
        String cameraId = "CAM-LIFE-1";
        registerCamera(token, cameraId);
        JsonNode detectionResponse = createIncidentFromDetection(token, cameraId, "MEDIUM", 70);
        String incidentId = detectionResponse.get("incidentId").asText();

        mockMvc.perform(put("/api/incidents/{id}/status", incidentId)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("""
                                {
                                  "status": "VERIFIED",
                                  "note": "Operator verified incident."
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(incidentId))
                .andExpect(jsonPath("$.status").value("VERIFIED"));
    }

    @Test
    void incidentRejectsInvalidLifecycleTransition() throws Exception {
        String token = loginAndGetToken();
        String cameraId = "CAM-LIFE-2";
        registerCamera(token, cameraId);
        JsonNode detectionResponse = createIncidentFromDetection(token, cameraId, "MEDIUM", 70);
        String incidentId = detectionResponse.get("incidentId").asText();

        mockMvc.perform(put("/api/incidents/{id}/status", incidentId)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("""
                                {
                                  "status": "RESOLVED",
                                  "note": "Invalid direct transition."
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409));
    }
}
