package com.catbytes.reviews.controller;

import com.catbytes.reviews.dto.UserDTO;
import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.mapper.UserMapper;
import com.catbytes.reviews.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody UserDTO request) {
        User registeredUser = userService.registerUser(request.getEmail(), request.getName());
        return userMapper.toDTO(registeredUser);
    }

}
