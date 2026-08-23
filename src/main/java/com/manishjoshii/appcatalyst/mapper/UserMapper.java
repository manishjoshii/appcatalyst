package com.manishjoshii.appcatalyst.mapper;

import com.manishjoshii.appcatalyst.dto.auth.SignupRequest;
import com.manishjoshii.appcatalyst.dto.auth.UserProfileResponse;
import com.manishjoshii.appcatalyst.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);

}
