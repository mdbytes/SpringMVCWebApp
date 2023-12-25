package com.mdbytes.club.repository;

import com.mdbytes.club.model.Club;
import com.mdbytes.club.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClubRepositoryTest {

    private ClubRepository clubRepository;

    private UserRepository userRepository;

    @Autowired
    public ClubRepositoryTest(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Test
    public void ClubRepository_SaveAll_Return_Saved_Clubs() {
        UserEntity martin = UserEntity.builder().username("me@me.com").email("me@me.com").password("test123").build();

        userRepository.save(martin);

        // Arrange
        Club club = Club
            .builder()
            .title("Strength Club")
            .photoUrl("/pics/1")
            .content("A club for you!")
            .createdBy(martin)
            .build();

        // Act
        Club savedClub = clubRepository.save(club);

        // Assert
        Assertions.assertThat(savedClub).isNotNull();
        Assertions.assertThat(savedClub.getId()).isGreaterThan(0);
    }

    @Test
    public void ClubRepository_findClubsByUser_Return_Saved_Clubs() {
        UserEntity martin = UserEntity.builder().username("me@me.com").email("me@me.com").password("test123").build();

        userRepository.save(martin);

        // Arrange
        Club clubOne = Club
            .builder()
            .title("Strength Club")
            .photoUrl("/pics/1")
            .content("A club for you!")
            .createdBy(martin)
            .build();
        clubRepository.save(clubOne);

        Club clubTwo = Club
            .builder()
            .title("Fitness Club")
            .photoUrl("/pics/1")
            .content("A club for you!")
            .createdBy(martin)
            .build();
        clubRepository.save(clubTwo);

        Club clubThree = Club
            .builder()
            .title("Strongman Club")
            .photoUrl("/pics/1")
            .content("A club for you!")
            .createdBy(martin)
            .build();
        clubRepository.save(clubThree);

        // Act
        List<Club> martinsClubs = clubRepository.findClubsByCreatedBy(martin);

        // Assert
        Assertions.assertThat(martinsClubs).isNotNull();
        Assertions.assertThat(martinsClubs.size()).isEqualTo(3);
    }

    @Test
    public void ClubRepository_searchClubs_Returns_Matches() {
        UserEntity martin = UserEntity.builder().username("me@me.com").email("me@me.com").password("test123").build();

        userRepository.save(martin);

        // Arrange
        Club clubOne = Club
            .builder()
            .title("Strength Club")
            .photoUrl("/pics/1")
            .content("A club for you!")
            .createdBy(martin)
            .build();
        clubRepository.save(clubOne);

        Club clubTwo = Club
            .builder()
            .title("Fitness Club")
            .photoUrl("/pics/1")
            .content("A club for you!")
            .createdBy(martin)
            .build();
        clubRepository.save(clubTwo);

        Club clubThree = Club
            .builder()
            .title("Strongman Club")
            .photoUrl("/pics/1")
            .content("A club for you!")
            .createdBy(martin)
            .build();
        clubRepository.save(clubThree);

        // Act
        List<Club> martinsClubs = clubRepository.searchClubs("Club");

        // Assert
        Assertions.assertThat(martinsClubs).isNotNull();
        Assertions.assertThat(martinsClubs.size()).isEqualTo(3);
    }
}
