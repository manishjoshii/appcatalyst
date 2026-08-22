package com.manishjoshii.appcatalyst.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
