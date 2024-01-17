package com.mdbytes.club.service.implementation;

import com.mdbytes.club.dto.EventDto;
import com.mdbytes.club.model.Club;
import com.mdbytes.club.model.Event;
import com.mdbytes.club.repository.ClubRepository;
import com.mdbytes.club.repository.EventRepository;
import com.mdbytes.club.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.mdbytes.club.dto.mapper.EventMapper.mapToEvent;
import static com.mdbytes.club.dto.mapper.EventMapper.mapToEventDto;


@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Integer clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAll() {
        List<Event> events = eventRepository.findAll();
        events.sort((a, b) -> a.getStartTime().compareTo(b.getStartTime()));
        return eventRepository.findAll().stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> searchEvents(String query) {
        List<Event> events = eventRepository.searchEvents(query);
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findById(Integer id) {
        Event event = eventRepository.findById(id).get();

        return mapToEventDto(event);
    }

    @Override
    public void save(EventDto eventDto) {
        eventRepository.save(mapToEvent(eventDto));
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void delete(EventDto eventDto) {
        eventRepository.delete(mapToEvent(eventDto));
    }


}
