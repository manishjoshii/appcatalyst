package com.manishjoshii.appcatalyst.mapper;

import com.manishjoshii.appcatalyst.dto.project.ProjectResponse;
import com.manishjoshii.appcatalyst.dto.project.ProjectSummaryResponse;
import com.manishjoshii.appcatalyst.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    @Mapping(target = "projectName", source = "name")
    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);

}
