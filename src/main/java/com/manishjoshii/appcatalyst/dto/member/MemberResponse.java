package com.manishjoshii.appcatalyst.dto.member;

import com.manishjoshii.appcatalyst.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String email,
        String name,
        String avatarUrl,
        ProjectRole role,
        Instant invitedAt
) {
}
