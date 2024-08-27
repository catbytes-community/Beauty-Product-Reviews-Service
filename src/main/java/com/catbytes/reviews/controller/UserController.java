package com.catbytes.reviews.controller;

import com.catbytes.reviews.dto.UserRegistrationRequest;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
        User registeredUser = userService.registerUser(request.getEmail(), request.getName());
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}

