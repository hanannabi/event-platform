package com.example.event_management.controller;

import com.example.event_management.dto.ReservationRequest;
import com.example.event_management.dto.ReservationResponse;
import com.example.event_management.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ReservationResponse reserve(@RequestBody ReservationRequest request) {
        ReservationResponse reservationResponse = reservationService.reserveTicket(request);
        return reservationResponse;
    }

    @DeleteMapping("cancel/{reservationId}")
    public String cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return "Reservation cancelled successfully";
    }

    @GetMapping("/users/{userId}")
    public List<ReservationResponse> getUserReservations(@PathVariable Long userId) {
        return reservationService.getUserReservations(userId);
    }
}
