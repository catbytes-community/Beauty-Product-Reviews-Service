package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.User;
import com.catbytes.reviews.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(@NotBlank @Email String email, String name) {
        LOG.debug("Attempting to register user with email: {} and name: {}", email, name);

        if (userRepository.existsByEmail(email)) {
            LOG.warn("Attempt to register with an existing email: {}", email);
            throw new IllegalArgumentException("User with this email is already registered");
        }

        LOG.debug("No existing user found with email: {}", email);
        LOG.info("Registering new user with email: {} and name: {}", email, name);

        User user = new User(email, name);
        User savedUser = userRepository.save(user);

        LOG.debug("User successfully registered with ID: {}", savedUser.getId());
        return savedUser;
    }
}

