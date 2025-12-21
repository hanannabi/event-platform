package com.example.event_management.advice;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timeStamp;
    private String message;
    private String details;

    public ErrorResponse(LocalDateTime timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public static ErrorResponse of(LocalDateTime timeStamp, String message, String details) {
        return new ErrorResponse(timeStamp, message, details);
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
