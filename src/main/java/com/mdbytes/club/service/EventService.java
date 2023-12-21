package com.mdbytes.club.service;

import com.mdbytes.club.dto.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    void createEvent(Integer clubId, EventDto eventDto);

    List<EventDto> findAll();

    List<EventDto> searchEvents(String query);

    EventDto findById(Integer id);

    void save(EventDto eventDto);

    void updateEvent(EventDto eventDto);

    void delete(EventDto eventDto);
}
