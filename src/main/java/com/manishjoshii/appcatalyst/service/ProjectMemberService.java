package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.member.InviteMemberRequest;
import com.manishjoshii.appcatalyst.dto.member.MemberResponse;
import com.manishjoshii.appcatalyst.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId);

    MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId);
}
