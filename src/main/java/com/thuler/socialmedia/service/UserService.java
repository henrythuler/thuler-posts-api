package com.thuler.socialmedia.service;

import com.thuler.socialmedia.dto.UserDTO;
import com.thuler.socialmedia.exception.NotFoundException;
import com.thuler.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO findById(String id){
        return new UserDTO(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User")));
    }

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

}
