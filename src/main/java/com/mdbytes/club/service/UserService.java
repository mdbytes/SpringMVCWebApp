package com.mdbytes.club.service;

import com.mdbytes.club.dto.RegistrationDto;
import com.mdbytes.club.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    List<UserEntity> findAll();

    UserEntity save(UserEntity user);

    void delete(UserEntity user);

    UserEntity getCurrentUser();
}
