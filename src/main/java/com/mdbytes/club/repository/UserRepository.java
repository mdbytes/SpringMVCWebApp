package com.mdbytes.club.repository;

import com.mdbytes.club.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findFirstByUsername(String username);
}
