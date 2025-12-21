package com.example.event_management.mapper;

import com.example.event_management.dto.EventRequest;
import com.example.event_management.dto.EventResponse;
import com.example.event_management.entity.Event;

public class EventMapper {
    public static Event toEntity(EventRequest request) {
        return new Event(
                request.getName(),
                request.getDate(),
                request.getLocation(),
                request.getCapacity(),
                request.getDescription()
        );

    }

    public static EventResponse toDto(Event entity) {
        return new EventResponse(
                entity.getId(),
                entity.getName(),
                entity.getDate(),
                entity.getLocation(),
                entity.getCapacity(),
                entity.getDescription(),
                entity.getAvailableSeats(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
