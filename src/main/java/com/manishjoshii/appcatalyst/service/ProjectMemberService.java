package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.member.InviteMemberRequest;
import com.manishjoshii.appcatalyst.dto.member.MemberResponse;
import com.manishjoshii.appcatalyst.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void removeProjectMember(Long projectId, Long memberId);
}
