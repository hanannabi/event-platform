package com.example.event_management.service;

import com.example.event_management.dto.EventRequest;
import com.example.event_management.dto.EventResponse;
import com.example.event_management.entity.Event;
import com.example.event_management.enums.Status;
import com.example.event_management.mapper.EventMapper;
import com.example.event_management.exception.CapacityShouldBeGreaterThan1Exception;
import com.example.event_management.exception.InvalidDateException;
import com.example.event_management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventResponse createEvent(EventRequest request) {
        if (request.getCapacity() < 1) {
            throw new CapacityShouldBeGreaterThan1Exception("Capacity must be above 10 person to book any event");
        }
        if (request.getDate().isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("Event date must be in the future");
        }
        Event entity = EventMapper.toEntity(request);
        entity.setAvailableSeats(request.getCapacity());
        entity.setStatus(Status.ONGOING);
        entity.setCreatedAt(LocalDateTime.now());
        eventRepository.save(entity);
        EventResponse eventResponse = EventMapper.toDto(entity);
        return eventResponse;


    }
}
