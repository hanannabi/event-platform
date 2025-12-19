package com.example.event_management.controller;

import com.example.event_management.dto.EventRequest;
import com.example.event_management.dto.EventResponse;
import com.example.event_management.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/events")
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventResponse createEvent(@RequestBody EventRequest request){
        EventResponse response = eventService.createEvent(request);
        return response;

    }
}
