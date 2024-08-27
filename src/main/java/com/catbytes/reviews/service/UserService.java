package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String email, String name) {
        User user = new User(email, name);
        return userRepository.save(user);
    }
}

