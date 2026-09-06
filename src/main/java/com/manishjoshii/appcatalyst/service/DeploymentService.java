package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.deploy.DeployResponse;

public interface DeploymentService {

    DeployResponse deploy(Long projectId);
}
