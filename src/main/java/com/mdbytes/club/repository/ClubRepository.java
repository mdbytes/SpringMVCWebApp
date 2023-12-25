package com.mdbytes.club.repository;

import com.mdbytes.club.model.Club;
import com.mdbytes.club.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    @Query("SELECT c from clubs c WHERE c.title LIKE CONCAT('%',:query,'%')")
    List<Club> searchClubs(String query);

    List<Club> findClubsByCreatedBy(UserEntity user);
}
