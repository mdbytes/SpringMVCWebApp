package com.mdbytes.club.service.implementation;

import com.mdbytes.club.dto.ClubDto;
import com.mdbytes.club.model.Club;
import com.mdbytes.club.model.UserEntity;
import com.mdbytes.club.repository.ClubRepository;
import com.mdbytes.club.repository.UserRepository;
import com.mdbytes.club.security.SecurityUtil;
import com.mdbytes.club.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.mdbytes.club.dto.mapper.ClubMapper.mapToClub;
import static com.mdbytes.club.dto.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;

    private UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club save(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findById(Integer id) {
        Club club = clubRepository.findById(id).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void delete(Integer id) {
        clubRepository.deleteById(id);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);

        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

}
