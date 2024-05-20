package com.thuler.socialmedia.repository;

import com.thuler.socialmedia.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    public List<Post> findByTitleContainingIgnoreCase(String title);
}
