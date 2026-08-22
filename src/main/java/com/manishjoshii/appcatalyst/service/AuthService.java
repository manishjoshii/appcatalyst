package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.auth.AuthResponse;
import com.manishjoshii.appcatalyst.dto.auth.LoginRequest;
import com.manishjoshii.appcatalyst.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
