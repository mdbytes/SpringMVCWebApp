package com.mdbytes.club.service;

import com.mdbytes.club.dto.ClubDto;
import com.mdbytes.club.model.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club save(ClubDto clubDto);

    ClubDto findById(Integer id);

    void updateClub(ClubDto club);

    void delete(Integer id);

    List<ClubDto> searchClubs(String query);


}
