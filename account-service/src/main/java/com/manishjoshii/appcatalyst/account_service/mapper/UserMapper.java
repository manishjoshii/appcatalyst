package com.manishjoshii.appcatalyst.account_service.mapper;

import com.manishjoshii.appcatalyst.account_service.dto.auth.SignupRequest;
import com.manishjoshii.appcatalyst.account_service.dto.auth.UserProfileResponse;
import com.manishjoshii.appcatalyst.account_service.entity.User;
import com.manishjoshii.appcatalyst.common_lib.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);

    UserDto toUserDto(User user);

}