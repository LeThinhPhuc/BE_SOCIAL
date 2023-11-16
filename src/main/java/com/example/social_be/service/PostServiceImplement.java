package com.example.social_be.service;

import com.example.social_be.model.Post;
import com.example.social_be.model.User;
import com.example.social_be.repository.PostRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImplement implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post addPost(Post post) {
        if (post == null) return null;

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(String id, Post post) {
        if (post == null) return null;

        try {
            Post postReturned = postRepository.findPostById(id);


            postReturned.update(post);
            return postRepository.save(postReturned);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Post> listPost(String userId) {
        try {
            return postRepository.findByUserId(userId);

        } catch (Exception e) {
            return null;
        }
    }
}
