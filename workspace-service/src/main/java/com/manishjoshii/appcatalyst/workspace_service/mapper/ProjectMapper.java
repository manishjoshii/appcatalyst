package com.manishjoshii.appcatalyst.workspace_service.mapper;

import com.manishjoshii.appcatalyst.common_lib.enums.ProjectRole;
import com.manishjoshii.appcatalyst.workspace_service.dto.project.ProjectResponse;
import com.manishjoshii.appcatalyst.workspace_service.dto.project.ProjectSummaryResponse;
import com.manishjoshii.appcatalyst.workspace_service.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project, ProjectRole role);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);

}