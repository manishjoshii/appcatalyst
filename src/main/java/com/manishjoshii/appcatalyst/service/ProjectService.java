package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.project.ProjectRequest;
import com.manishjoshii.appcatalyst.dto.project.ProjectResponse;
import com.manishjoshii.appcatalyst.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects(Long userId);

    ProjectResponse getUserProjectById(Long id, Long userId);

    ProjectResponse createProject(ProjectRequest request, Long userId);

    ProjectResponse updateProject(Long id, ProjectRequest request, Long userId);

    void softDelete(Long id, Long userId);
}
