package com.manishjoshii.appcatalyst.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {

}
