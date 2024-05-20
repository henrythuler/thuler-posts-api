package com.thuler.socialmedia.controller;

import com.thuler.socialmedia.model.Comment;
import com.thuler.socialmedia.model.Post;
import com.thuler.socialmedia.service.PostService;
import com.thuler.socialmedia.util.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitleContaining(@RequestParam(value = "title", defaultValue = "") String title){
        title = URL.decodeParam(title);
        return ResponseEntity.ok(postService.findByTitleContaining(title));
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> findByTitleContaining(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "2000-01-01") LocalDate minDate,
            @RequestParam(value = "maxDate", defaultValue = "2100-12-31") LocalDate maxDate)
    {
        text = URL.decodeParam(text);
        minDate = LocalDate.parse(URL.decodeParam(minDate.toString()));
        maxDate = LocalDate.parse(URL.decodeParam(maxDate.toString()));

        return ResponseEntity.ok(postService.fullSearch(text, minDate, maxDate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@RequestBody Post post, @PathVariable String id){
        return ResponseEntity.ok(postService.update(post, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable String id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
