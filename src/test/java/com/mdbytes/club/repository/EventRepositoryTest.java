package com.mdbytes.club.repository;

import com.mdbytes.club.model.Club;
import com.mdbytes.club.model.Event;
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
public class EventRepositoryTest {

    private EventRepository eventRepository;

    private UserRepository userRepository;

    private ClubRepository clubRepository;

    @Autowired
    public EventRepositoryTest(
        EventRepository eventRepository,
        UserRepository userRepository,
        ClubRepository clubRepository
    ) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.clubRepository = clubRepository;
    }

    @Test
    public void EventRepository_searchEvents_Returns_Matches() {
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

        Event eventOne = Event.builder().name("Powerlifters Banquet").photoUrl("pics/1").club(clubOne).build();
        Event eventTwo = Event.builder().name("Weightlifters Banquet").photoUrl("pics/1").club(clubOne).build();
        Event eventThree = Event.builder().name("Strongman Banquet").photoUrl("pics/1").club(clubOne).build();

        eventRepository.save(eventOne);
        eventRepository.save(eventTwo);
        eventRepository.save(eventThree);

        // Act
        List<Event> banquetEvents = eventRepository.searchEvents("Banquet");

        // Assert
        Assertions.assertThat(banquetEvents).isNotNull();
        Assertions.assertThat(banquetEvents.size()).isEqualTo(3);
    }
}
