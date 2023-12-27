package com.mdbytes.club.service;

import com.mdbytes.club.dto.RegistrationDto;
import com.mdbytes.club.dto.mapper.UserMapper;
import com.mdbytes.club.model.Role;
import com.mdbytes.club.model.UserEntity;
import com.mdbytes.club.repository.RoleRepository;
import com.mdbytes.club.repository.UserRepository;
import com.mdbytes.club.service.implementation.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void UserService_Save_ReturnsUser() {
        UserEntity user = UserEntity.builder().username("me@me.com").email("me@me.com").password("test123").build();

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);

        UserEntity savedUser = userService.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        //Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserService_SaveUser_ReturnsUser() {
        RegistrationDto registrationDto = RegistrationDto
            .builder()
            .id(1)
            .username("me@me.com")
            .email("me@me.com")
            .password("test123")
            .build();

        Role adminRole = Role.builder().id(1).name("ADMIN").build();

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(UserMapper.mapToUser(registrationDto));

        when(passwordEncoder.encode(Mockito.any(String.class))).thenReturn("test123");

        when(roleRepository.findByName(Mockito.any(String.class))).thenReturn(adminRole);

        UserEntity savedUser = userService.saveUser(registrationDto);

        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void UserService_FindByEmail_ReturnsCorrectUser() {
        UserEntity user = UserEntity.builder().username("me@me.com").email("me@me.com").password("test123").build();

        when(userRepository.findByEmail(Mockito.any(String.class))).thenReturn(user);

        UserEntity savedUser = userService.findByEmail("me@me.com");

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser).isEqualTo(user);
    }
}
