package com.aegissight.detection;

import com.aegissight.IntegrationTestSupport;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DetectionFlowIntegrationTest extends IntegrationTestSupport {

    @Test
    void detectionCreatesIncidentAndAlert() throws Exception {
        String token = loginAndGetToken();
        String cameraId = "CAM-FLOW-1";
        registerCamera(token, cameraId);

        JsonNode detectionResponse = createIncidentFromDetection(token, cameraId, "HIGH", 87);

        String incidentId = detectionResponse.get("incidentId").asText();

        mockMvc.perform(get("/api/incidents/{id}", incidentId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(incidentId))
                .andExpect(jsonPath("$.sourceId").value(cameraId))
                .andExpect(jsonPath("$.status").value("NEW"))
                .andExpect(jsonPath("$.latestDetection.confidence").value(0.94));

        mockMvc.perform(get("/api/alerts")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].sourceId").value(cameraId))
                .andExpect(jsonPath("$.items[0].status").value("OPEN"));
    }
}
