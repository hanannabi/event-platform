package com.example.event_management.service;

import com.example.event_management.client.UserClient;
import com.example.event_management.dto.UserResponse;
import com.example.event_management.exception.UserServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private final UserClient userClient;

    public UserValidationService(UserClient userClient) {
        this.userClient = userClient;
    }

    //apply resilience4j to feign call to handle fallbacks and retries

    @CircuitBreaker(name = "userService", fallbackMethod = "userFallback")
    @Retry(name = "userService")
    public UserResponse validateUser(Long userId) {
        return userClient.getUserById(userId);
    }

    //Fallback method
    public UserResponse userFallback(Long userId, Throwable ex) throws UserServiceUnavailableException {
        throw new UserServiceUnavailableException(
                "User service is temporarily unavailable. Please try later."
        );
    }
}
