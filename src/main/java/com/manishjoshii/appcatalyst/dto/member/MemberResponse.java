package com.manishjoshii.appcatalyst.dto.member;

import com.manishjoshii.appcatalyst.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole role,
        Instant invitedAt
) {
}
