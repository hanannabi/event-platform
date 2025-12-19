package com.example.event_management.exception;

public class EventFullyBookedException extends RuntimeException {
    public EventFullyBookedException(String message) {
        super(message);
    }
}
