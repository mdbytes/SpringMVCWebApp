package com.mdbytes.club.controller;

import com.mdbytes.club.dto.EventDto;
import com.mdbytes.club.model.UserEntity;
import com.mdbytes.club.security.SecurityUtil;
import com.mdbytes.club.service.EventService;
import com.mdbytes.club.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Controller
public class EventController {

    private EventService eventService;

    private UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        List<EventDto> eventDtoList = eventService.findAll();
        model.addAttribute("events", eventDtoList);
        UserEntity user = getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "events/events-list";
    }

    @GetMapping("/events/{id}/new")
    public String createEvent(@PathVariable("id") Integer id, Model model) {
        EventDto eventDto = new EventDto();
        model.addAttribute("id", id);
        model.addAttribute("event", eventDto);
        return "events/events-create";
    }

    @PostMapping("/events/{id}/new")
    public String addEvent(@ModelAttribute("event") EventDto eventDto, @PathVariable("id") Integer id) {
        eventService.createEvent(id, eventDto);
        return "redirect:/clubs/" + id;
    }

    @GetMapping("/events/search")
    public String searchEvents(@RequestParam(value = "query") String query, Model model) {
        List<EventDto> eventDtoList = eventService.searchEvents(query);
        model.addAttribute("events", eventDtoList);
        UserEntity user = getCurrentUser();
        model.addAttribute("user", user);
        return "events/events-list";
    }

    @GetMapping("/events/{id}")
    public String eventDetail(@PathVariable("id") Integer id, Model model) {
        EventDto eventDto = eventService.findById(id);
        model.addAttribute("event", eventDto);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        model.addAttribute("df", dateTimeFormatter);
        UserEntity user = getCurrentUser();
        model.addAttribute("user", user);
        return "events/events-detail";
    }

    @GetMapping("/events/{id}/edit")
    public String updateEvent(@PathVariable("id") Integer id, Model model) {
        EventDto eventDto = eventService.findById(id);
        model.addAttribute("event", eventDto);
        return "events/events-edit";
    }

    @GetMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable("id") Integer id, Model model) {
        EventDto eventDto = eventService.findById(id);
        eventService.delete(eventDto);
        return "redirect:/events";
    }

    @PostMapping("/events/{eventId}/edit")
    public String editEvent(
        @PathVariable("eventId") Integer eventId,
        @Valid @ModelAttribute("event") EventDto eventDto,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "events/events-edit";
        }
        EventDto event = eventService.findById(eventId);
        eventDto.setClub(event.getClub());
        eventDto.setEventId(eventId);
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    private UserEntity getCurrentUser() {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userService.findByUsername(username);
        return user;
    }
}
