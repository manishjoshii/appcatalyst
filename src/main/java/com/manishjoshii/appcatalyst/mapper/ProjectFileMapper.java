package com.manishjoshii.appcatalyst.mapper;

import com.manishjoshii.appcatalyst.dto.project.FileNode;
import com.manishjoshii.appcatalyst.entity.ProjectFile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectFileMapper {

    List<FileNode> toListOfFileNode(List<ProjectFile> projectFileList);
}
