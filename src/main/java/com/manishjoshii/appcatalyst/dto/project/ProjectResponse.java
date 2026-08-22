package com.manishjoshii.appcatalyst.dto.project;

import com.manishjoshii.appcatalyst.dto.auth.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt,
        UserProfileResponse owner
) {
}
