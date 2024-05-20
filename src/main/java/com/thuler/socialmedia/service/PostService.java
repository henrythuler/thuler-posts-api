package com.thuler.socialmedia.service;

import com.thuler.socialmedia.exception.NotFoundException;
import com.thuler.socialmedia.model.Post;
import com.thuler.socialmedia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public ResponseEntity<Post> findById(String id){
        return ResponseEntity.ok(postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post")));
    }

}
