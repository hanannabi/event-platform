package com.example.event_management.service;

import com.example.event_management.client.UserClient;
import com.example.event_management.dto.ReservationRequest;
import com.example.event_management.dto.ReservationResponse;
import com.example.event_management.dto.UserResponse;
import com.example.event_management.entity.Event;
import com.example.event_management.entity.Reservation;
import com.example.event_management.exception.*;
import com.example.event_management.mapper.ReservationMapper;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.repository.ReservationRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final UserClient userClient;

    public ReservationService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserValidationService userValidationService;


    public ReservationResponse reserveTicket(ReservationRequest request) {
//        Users users = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new UserNotFoundException("User not found"));

        //feign call through resilience4j
        UserResponse user = userValidationService.validateUser(request.getUserId());
        //direct feign call without going through resilience4j to call user service, this was the motive
//        UserResponse user = userClient.getUserById(request.getUserId());
//        if (user==null){
//            throw new RuntimeException("Invalid user");
//        }

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        //prevent overbooking
        if (event.getAvailableSeats() <= 0) {
            throw new EventFullyBookedException("No available seats for this event");
        }
        //prevent double booking
        if (reservationRepository.existsByUserIdAndEventId(request.getUserId(), request.getEventId())) {
            throw new UserAlreadyBookedThisEventException("User has already booked a ticket for this event");
        }
        ;

        // Create Reservation
        Reservation reservation = ReservationMapper.toEntity(request);
        reservation.setUserId(request.getUserId());
        reservation.setEvent(event);
        reservation.setReservedAt(LocalDateTime.now());
        Reservation savedEntity = reservationRepository.save(reservation);

        // Decrease available seats
        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepository.save(event);

        //return response
        ReservationResponse reservationResponse = ReservationMapper.toDto(savedEntity);
        return reservationResponse;

    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        Event event = reservation.getEvent();
        event.setAvailableSeats(event.getAvailableSeats() + 1);
        eventRepository.save(event);
        reservationRepository.delete(reservation);
    }

    public List<ReservationResponse> getUserReservations(Long userId) {
        List<ReservationResponse> list = reservationRepository.findByUserId(userId)
                .stream()
                .map(ReservationMapper::toDto)
                .toList();
        return list;


    }
}
