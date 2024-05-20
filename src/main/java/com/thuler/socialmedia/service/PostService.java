package com.thuler.socialmedia.service;

import com.thuler.socialmedia.exception.NotFoundException;
import com.thuler.socialmedia.model.Comment;
import com.thuler.socialmedia.model.Post;
import com.thuler.socialmedia.model.User;
import com.thuler.socialmedia.repository.CommentRepository;
import com.thuler.socialmedia.repository.PostRepository;
import com.thuler.socialmedia.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Post create(Post post){
        Optional<User> foundUser = userRepository.findById(post.getAuthor().id());

        if(foundUser.isEmpty()) throw new NotFoundException("User");
        Post newPost = postRepository.save(post);

        foundUser.get().getPosts().add(newPost);
        userRepository.save(foundUser.get());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();

        return newPost;
    }

    public Comment createComment(Comment comment, String id){
        Optional<Post> foundPost = postRepository.findById(id);
        if(foundPost.isEmpty()) throw new NotFoundException("Post");

        Comment newComment = commentRepository.save(comment);
        foundPost.get().getComments().add(comment);
        postRepository.save(foundPost.get());
        return newComment;
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(String id){
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post"));
    }

    public List<Post> findByTitleContaining(String title){
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    public Post update(Post post, String id){
        if(!postRepository.existsById(id)) throw new NotFoundException("Post");
        post.setId(id);

        return postRepository.save(post);
    }

    public void delete(String id){
        if(!postRepository.existsById(id)) throw new NotFoundException("Post");
        postRepository.deleteById(id);
    }

}
