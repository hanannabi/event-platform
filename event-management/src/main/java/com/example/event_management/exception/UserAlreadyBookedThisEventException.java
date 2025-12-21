package com.example.event_management.exception;

public class UserAlreadyBookedThisEventException extends RuntimeException {
    public UserAlreadyBookedThisEventException(String message) {
        super(message);
    }
}
