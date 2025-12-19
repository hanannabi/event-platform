package com.example.event_management.advice;

import com.example.event_management.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(CapacityShouldBeGreaterThan1Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCapacityShouldBeGreaterThan1Exception(CapacityShouldBeGreaterThan1Exception exception) {
        return ErrorResponse.of(LocalDateTime.now(), exception.getMessage(), "Capacity must be above 10 person to book any event");

    }

    @ExceptionHandler(InvalidDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidDateException(InvalidDateException exception) {
        return ErrorResponse.of(LocalDateTime.now(), exception.getMessage(), "Event date must be in the future");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        return ErrorResponse.of(LocalDateTime.now(), exception.getMessage(), "User with the given ID does not exist");
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEventNotFoundException(EventNotFoundException exception) {
        return ErrorResponse.of(LocalDateTime.now(), exception.getMessage(), "Event with the given ID does not exist");
    }

    @ExceptionHandler(EventFullyBookedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEventFullyBookedException(EventFullyBookedException exception) {
        return ErrorResponse.of(LocalDateTime.now(), exception.getMessage(), "No available seats for this event");
    }

    @ExceptionHandler(UserAlreadyBookedThisEventException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserAlreadyBookedThisEventException(UserAlreadyBookedThisEventException exception) {
        return ErrorResponse.of(LocalDateTime.now(), exception.getMessage(), "User has already booked a ticket for this event");
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleReservationNotFoundException(ReservationNotFoundException exception) {
        return ErrorResponse.of(LocalDateTime.now(), exception.getMessage(), "Reservation with the given ID does not exist");
    }

    @ExceptionHandler(UserServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserServiceUnavailableException(UserServiceUnavailableException exception){
        return ErrorResponse.of(LocalDateTime.now(), exception.getMessage(), "User service is temporarily unavailable. Please try later.");
    }

}
