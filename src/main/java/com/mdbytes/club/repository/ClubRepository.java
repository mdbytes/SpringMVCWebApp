package com.mdbytes.club.repository;

import com.mdbytes.club.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    Optional<Club> findByTitle(String title);

    @Query("SELECT c from clubs c WHERE c.title LIKE CONCAT('%',:query,'%')")
    List<Club> searchClubs(String query);
}
