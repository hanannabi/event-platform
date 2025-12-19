package com.example.user_service.mapper;

import com.example.user_service.dto.Request;
import com.example.user_service.dto.Response;
import com.example.user_service.entity.Users;

public class UserMapper {
    public static Users toEntity(Request request) {
        return new Users(
                request.getName(),
                request.getEmail(),
                request.getMobile()
        );
    }

    public static Response toDto(Users savedUser) {
        return new Response(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getMobile()
        );
    }
}

