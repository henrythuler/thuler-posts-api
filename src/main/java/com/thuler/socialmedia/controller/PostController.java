package com.thuler.socialmedia.controller;

import com.thuler.socialmedia.model.Comment;
import com.thuler.socialmedia.model.Post;
import com.thuler.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post){
        Post newPost = postService.create(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(location).body(newPost);
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable String id){
        return ResponseEntity.ok(postService.createComment(comment, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        return ResponseEntity.ok(postService.findById(id));
    }

}
