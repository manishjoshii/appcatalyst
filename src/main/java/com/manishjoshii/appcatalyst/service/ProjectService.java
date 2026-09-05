package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.project.ProjectRequest;
import com.manishjoshii.appcatalyst.dto.project.ProjectResponse;
import com.manishjoshii.appcatalyst.dto.project.ProjectSummaryResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects();

    ProjectSummaryResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void softDelete(Long id);
}
