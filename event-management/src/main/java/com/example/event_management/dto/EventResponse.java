package com.example.event_management.dto;

import com.example.event_management.enums.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
public class EventResponse{
   private Long id;
    private String name;
    private LocalDateTime date;
    private String location;
    private Integer capacity;
    private String description;
    private Integer availableSeats;
    private Enum<Status> status;
    private LocalDateTime createdAt;

    public EventResponse(Long id, String name, LocalDateTime date, String location, Integer capacity, String description, Integer availableSeats, Enum<Status> status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.description = description;
        this.availableSeats = availableSeats;
        this.status = status;
        this.createdAt = createdAt;
    }

    public EventResponse(Long id, String name, LocalDateTime date, String location, Integer capacity, String description,int availableSeats) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.description = description;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Enum<Status> status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
