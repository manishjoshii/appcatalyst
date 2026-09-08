package com.manishjoshii.appcatalyst.account_service.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {

}