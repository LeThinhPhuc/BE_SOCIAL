package com.example.social_be.service;

import com.example.social_be.model.Post;

public interface PostService {
    public Post addPost(Post post);

    public Post updatePost(String id, Post post);
}
