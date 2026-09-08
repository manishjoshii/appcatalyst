package com.manishjoshii.appcatalyst.workspace_service.dto.member;

import com.manishjoshii.appcatalyst.common_lib.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role) {
}