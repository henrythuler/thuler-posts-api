package com.thuler.socialmedia.repository;

import com.thuler.socialmedia.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    public List<Post> findByTitleContainingIgnoreCase(String title);

    @Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    public List<Post> fullSearch(String text, LocalDate minDate, LocalDate maxDate);

}
