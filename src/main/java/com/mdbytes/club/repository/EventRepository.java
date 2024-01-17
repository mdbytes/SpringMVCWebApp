package com.mdbytes.club.repository;

import com.mdbytes.club.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT e from Event e WHERE e.name LIKE CONCAT('%',:query,'%')")
    List<Event> searchEvents(String query);
}
