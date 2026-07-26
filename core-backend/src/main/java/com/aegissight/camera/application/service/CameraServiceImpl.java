package com.aegissight.camera.application.service;

import com.aegissight.camera.api.dto.CameraResponse;
import com.aegissight.camera.api.dto.RegisterCameraRequest;
import com.aegissight.camera.api.mapper.CameraMapper;
import com.aegissight.camera.domain.entity.Camera;
import com.aegissight.camera.domain.exception.CameraAlreadyExistsException;
import com.aegissight.camera.domain.repository.CameraRepository;
import com.aegissight.common.domain.model.CameraPlatform;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CameraServiceImpl implements CameraService {

    private final CameraRepository cameraRepository;
    private final CameraMapper cameraMapper;

    public CameraServiceImpl(CameraRepository cameraRepository, CameraMapper cameraMapper) {
        this.cameraRepository = cameraRepository;
        this.cameraMapper = cameraMapper;
    }

    @Override
    @Transactional
    public CameraResponse register(RegisterCameraRequest request) {
        if (cameraRepository.existsById(request.id())) {
            throw new CameraAlreadyExistsException("Camera id already exists: " + request.id());
        }

        Camera camera = cameraMapper.toEntity(request);
        return cameraMapper.toResponse(cameraRepository.save(camera));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CameraResponse> list(CameraPlatform platform) {
        return cameraRepository.findAll(platform).stream()
                .map(cameraMapper::toResponse)
                .toList();
    }
}
