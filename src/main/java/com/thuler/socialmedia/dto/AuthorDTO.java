package com.thuler.socialmedia.dto;

public record AuthorDTO(
        String id,
        String name
) {

    public AuthorDTO(UserDTO userDTO){
        this(
            userDTO.id(),
            userDTO.name()
        );
    }

}
