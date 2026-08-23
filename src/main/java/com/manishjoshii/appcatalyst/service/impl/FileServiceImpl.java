package com.manishjoshii.appcatalyst.service.impl;

import com.manishjoshii.appcatalyst.dto.project.FileContentResponse;
import com.manishjoshii.appcatalyst.dto.project.FileNode;
import com.manishjoshii.appcatalyst.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
