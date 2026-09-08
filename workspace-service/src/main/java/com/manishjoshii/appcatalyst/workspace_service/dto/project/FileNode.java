package com.manishjoshii.appcatalyst.workspace_service.dto.project;

public record FileNode(
        String path
) {

    @Override
    public String toString() {
        return path;
    }
}