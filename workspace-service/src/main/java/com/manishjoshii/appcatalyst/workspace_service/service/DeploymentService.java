package com.manishjoshii.appcatalyst.workspace_service.service;

import com.manishjoshii.appcatalyst.workspace_service.dto.project.DeployResponse;
import org.jspecify.annotations.Nullable;

public interface DeploymentService {
    @Nullable DeployResponse deploy(Long projectId);
}