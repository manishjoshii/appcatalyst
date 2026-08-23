package com.manishjoshii.appcatalyst.service.impl;

import com.manishjoshii.appcatalyst.dto.project.ProjectRequest;
import com.manishjoshii.appcatalyst.dto.project.ProjectResponse;
import com.manishjoshii.appcatalyst.dto.project.ProjectSummaryResponse;
import com.manishjoshii.appcatalyst.entity.Project;
import com.manishjoshii.appcatalyst.entity.User;
import com.manishjoshii.appcatalyst.mapper.ProjectMapper;
import com.manishjoshii.appcatalyst.repository.ProjectRepository;
import com.manishjoshii.appcatalyst.repository.UserRepository;
import com.manishjoshii.appcatalyst.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {

        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();

        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public void softDelete(Long id, Long userId) {

    }
}
