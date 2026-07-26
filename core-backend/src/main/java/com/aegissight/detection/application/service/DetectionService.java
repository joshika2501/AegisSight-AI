package com.aegissight.detection.application.service;

import com.aegissight.detection.api.dto.DetectionIngestRequest;
import com.aegissight.detection.api.dto.DetectionIngestResponse;

public interface DetectionService {

    DetectionIngestResponse ingest(DetectionIngestRequest request);
}
