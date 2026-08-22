package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
