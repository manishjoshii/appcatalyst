package com.manishjoshii.appcatalyst.dto.auth;

public record SignupRequest(
        String email,
        String name,
        String password
) {
}
