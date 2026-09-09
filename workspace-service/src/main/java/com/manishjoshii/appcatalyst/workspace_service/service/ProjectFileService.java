package com.manishjoshii.appcatalyst.workspace_service.service;


import com.manishjoshii.appcatalyst.common_lib.dto.FileTreeDto;
import com.manishjoshii.appcatalyst.workspace_service.dto.project.FileContentResponse;

public interface ProjectFileService {
    FileTreeDto getFileTree(Long projectId);

    String getFileContent(Long projectId, String path);

    void saveFile(Long projectId, String filePath, String fileContent);
}