package com.manishjoshii.appcatalyst.service.impl;

import com.manishjoshii.appcatalyst.dto.auth.AuthResponse;
import com.manishjoshii.appcatalyst.dto.auth.LoginRequest;
import com.manishjoshii.appcatalyst.dto.auth.SignupRequest;
import com.manishjoshii.appcatalyst.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
