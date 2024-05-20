package com.thuler.socialmedia.repository;

import com.thuler.socialmedia.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
