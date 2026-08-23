package com.manishjoshii.appcatalyst.service.impl;

import com.manishjoshii.appcatalyst.dto.member.InviteMemberRequest;
import com.manishjoshii.appcatalyst.dto.member.MemberResponse;
import com.manishjoshii.appcatalyst.dto.member.UpdateMemberRoleRequest;
import com.manishjoshii.appcatalyst.service.ProjectMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId) {
        return null;
    }
}
