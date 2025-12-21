package com.example.event_management.repository;

import com.example.event_management.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    List<Reservation> findByUserId(Long userId);

}
