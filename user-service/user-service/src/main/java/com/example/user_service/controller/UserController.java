package com.example.user_service.controller;


import com.example.user_service.dto.Request;
import com.example.user_service.dto.Response;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Response registerUser(@RequestBody Request request){
        return userService.registerUser(request);
    }

    @GetMapping("/{id}")
    public Response getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<Response> getAllUsers() {
        return userService.getAllUsers();
    }

}

