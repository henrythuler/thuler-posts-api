package com.thuler.socialmedia.dto;

import com.thuler.socialmedia.model.User;

public record UserDTO(
        String id,
        String name,
        String email
) {

    public UserDTO(User user){
        this(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }

}
