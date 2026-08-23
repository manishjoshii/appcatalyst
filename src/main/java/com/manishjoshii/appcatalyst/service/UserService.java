package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.auth.UserProfileResponse;
import org.jspecify.annotations.Nullable;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
