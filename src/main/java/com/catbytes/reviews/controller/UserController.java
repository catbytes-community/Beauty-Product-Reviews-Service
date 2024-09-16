package com.catbytes.reviews.controller;

import com.catbytes.reviews.dto.UserDTO;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDTO registerUser(@Valid @RequestBody UserDTO request) {
        User registeredUser = userService.registerUser(request.getEmail(), request.getName());
        return userService.getUserDTO(registeredUser);
    }
}
