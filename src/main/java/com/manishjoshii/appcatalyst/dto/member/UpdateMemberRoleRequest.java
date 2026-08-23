package com.manishjoshii.appcatalyst.dto.member;

import com.manishjoshii.appcatalyst.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role) {
}
