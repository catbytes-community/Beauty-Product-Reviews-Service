package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.UserDTO;
import com.catbytes.reviews.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getName());
    }

    public User toEntity(UserDTO userDTO) {

        return new User(userDTO.getEmail(), userDTO.getName());
    }
}
