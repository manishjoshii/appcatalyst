package com.manishjoshii.appcatalyst.workspace_service.dto.member;

import com.manishjoshii.appcatalyst.common_lib.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole role,
        Instant invitedAt
) {
}