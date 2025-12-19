package com.example.event_management.mapper;

import com.example.event_management.dto.ReservationRequest;
import com.example.event_management.dto.ReservationResponse;
import com.example.event_management.entity.Reservation;

public class ReservationMapper {
    public static Reservation toEntity(ReservationRequest request) {
    return new Reservation(request.getEventId(), request.getUserId());
    }

    public static ReservationResponse toDto(Reservation savedEntity) {
        return new ReservationResponse(
            savedEntity.getId(),
            savedEntity.getEvent().getId(),
            savedEntity.getUserId(),
            savedEntity.getEvent().getName(),
            savedEntity.getReservedAt()
        );
    }
}
