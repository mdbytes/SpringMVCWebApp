package com.mdbytes.club.repository;

import com.mdbytes.club.model.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void RoleRepository_findByName_Returns_Role() {
        Role role = Role.builder().name("USER").build();
        roleRepository.save(role);

        Role retrievedRole = roleRepository.findByName("USER");

        Assertions.assertThat(retrievedRole).isNotNull();
        Assertions.assertThat(retrievedRole.getName()).isEqualTo("USER");
    }
}
