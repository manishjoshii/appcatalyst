package com.manishjoshii.appcatalyst.service.impl;

import com.manishjoshii.appcatalyst.dto.auth.UserProfileResponse;
import com.manishjoshii.appcatalyst.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
