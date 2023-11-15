package com.example.social_be.controller;

import com.example.social_be.model.Post;
import com.example.social_be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    public final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService=postService;
    }

    @PostMapping("/add")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @GetMapping("/list/{userId}")
    public List<Post> listPost(@PathVariable("userId") String userId){
        return postService.listPost(userId);
    }

}
