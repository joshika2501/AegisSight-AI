package com.aegissight.detection.api.controller;

import com.aegissight.detection.api.dto.DetectionIngestRequest;
import com.aegissight.detection.api.dto.DetectionIngestResponse;
import com.aegissight.detection.application.service.DetectionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detections")
public class DetectionController {

    private final DetectionService detectionService;

    public DetectionController(DetectionService detectionService) {
        this.detectionService = detectionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetectionIngestResponse ingest(@Valid @RequestBody DetectionIngestRequest request) {
        return detectionService.ingest(request);
    }
}
