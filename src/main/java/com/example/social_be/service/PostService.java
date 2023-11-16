
package com.example.social_be.service;

import com.example.social_be.model.Post;

import java.util.List;

public interface PostService {
    public Post addPost(Post post);
    public boolean deletePost(String postId);
    public Post updatePost(String id, Post post);
    public List<Post> listPost(String userId);
    public List<Post> getAllPost();
    public Post getPost(String postId);
}
