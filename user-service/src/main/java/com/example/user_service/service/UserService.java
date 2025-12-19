package com.example.user_service.service;

import com.example.user_service.dto.Request;
import com.example.user_service.dto.Response;
import com.example.user_service.entity.Users;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Response registerUser(Request request) {
        Users UserEntity = UserMapper.toEntity(request);
        Users savedUser = userRepository.save(UserEntity);
        return UserMapper.toDto(savedUser);
    }

    public Response getUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserMapper.toDto(user);
    }

    public List<Response> getAllUsers() {
        List<Response> userResponseList = userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
        return userResponseList;
    }
}

