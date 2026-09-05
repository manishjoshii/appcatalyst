package com.manishjoshii.appcatalyst.mapper;

import com.manishjoshii.appcatalyst.dto.project.ProjectResponse;
import com.manishjoshii.appcatalyst.dto.project.ProjectSummaryResponse;
import com.manishjoshii.appcatalyst.entity.Project;
import com.manishjoshii.appcatalyst.enums.ProjectRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project, ProjectRole role);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);

}
