package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.project.FileContentResponse;
import com.manishjoshii.appcatalyst.dto.project.FileNode;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
