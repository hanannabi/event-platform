package com.example.event_management.dto;

import java.time.LocalDateTime;

public class ReservationResponse {
    private Long reservationId;
    private Long eventId;
    private Long userId;
    private String eventName;
    private LocalDateTime reservedAt;

    public ReservationResponse(Long reservationId, Long eventId, Long userId, String eventName, LocalDateTime reservedAt) {
        this.reservationId = reservationId;
        this.eventId = eventId;
        this.userId = userId;
        this.eventName = eventName;
        this.reservedAt = reservedAt;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }
}
