package com.mdbytes.club.dto.mapper;

import com.mdbytes.club.dto.RegistrationDto;
import com.mdbytes.club.model.UserEntity;

public class UserMapper {

    public static UserEntity mapToUser(RegistrationDto dto) {
        return UserEntity
            .builder()
            .username(dto.getUsername())
            .id(dto.getId())
            .email(dto.getEmail())
            .password(dto.getPassword())
            .build();
    }
}
