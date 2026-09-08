package com.manishjoshii.appcatalyst.workspace_service.mapper;

import com.manishjoshii.appcatalyst.workspace_service.dto.member.MemberResponse;
import com.manishjoshii.appcatalyst.workspace_service.entity.ProjectMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id.userId")
    @Mapping(target = "role", source = "projectRole")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);
}