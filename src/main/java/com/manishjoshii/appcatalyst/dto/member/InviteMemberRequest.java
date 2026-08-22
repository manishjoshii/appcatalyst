package com.manishjoshii.appcatalyst.dto.member;

import com.manishjoshii.appcatalyst.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
