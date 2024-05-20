package com.thuler.socialmedia.repository;

import com.thuler.socialmedia.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
