package com.mdbytes.club.repository;

import com.mdbytes.club.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void UserRepository_SaveAll_Return_Saved_Clubs() {
        // Arrange
        UserEntity martin = UserEntity.builder().username("me@me.com").email("me@me.com").password("test123").build();

        userRepository.save(martin);

        // Act
        UserEntity savedUser = userRepository.save(martin);

        // Assert
        Assertions.assertThat(martin).isNotNull();
        Assertions.assertThat(martin.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_findByEmail_Returns_User() {
        // Arrange
        UserEntity martin = UserEntity.builder().username("me@me.com").email("me@me.com").password("test123").build();
        userRepository.save(martin);

        // Act
        UserEntity retrievedUser = userRepository.findByEmail("me@me.com");

        // Assert
        Assertions.assertThat(retrievedUser).isNotNull();
        Assertions.assertThat(retrievedUser.getEmail()).isEqualTo("me@me.com");
    }

    @Test
    public void UserRepository_findByUsername_Returns_User() {
        // Arrange
        UserEntity martin = UserEntity.builder().username("me@me.com").email("me@me.com").password("test123").build();
        userRepository.save(martin);

        // Act
        UserEntity retrievedUser = userRepository.findByUsername("me@me.com");

        // Assert
        Assertions.assertThat(retrievedUser).isNotNull();
        Assertions.assertThat(retrievedUser.getEmail()).isEqualTo("me@me.com");
    }

    @Test
    public void UserRepository_findFirstByUsername_Returns_User() {
        // Arrange
        UserEntity martin = UserEntity.builder().username("me@me.com").email("me@me.com").password("test123").build();
        userRepository.save(martin);

        // Act
        UserEntity retrievedUser = userRepository.findFirstByUsername("me@me.com");

        // Assert
        Assertions.assertThat(retrievedUser).isNotNull();
        Assertions.assertThat(retrievedUser.getEmail()).isEqualTo("me@me.com");
    }
}
