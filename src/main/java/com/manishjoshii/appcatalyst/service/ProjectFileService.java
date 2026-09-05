package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.project.FileContentResponse;
import com.manishjoshii.appcatalyst.dto.project.FileTreeResponse;

public interface ProjectFileService {
    FileTreeResponse getFileTree(Long projectId);

    FileContentResponse getFileContent(Long projectId, String path);

    void saveFile(Long projectId, String filePath, String fileContent);
}
