package com.example.social_be.controller;

import com.example.social_be.model.Post;
import com.example.social_be.model.User;
import com.example.social_be.repository.PostRepository;
import com.example.social_be.repository.UserRepository;
import com.example.social_be.service.PostService;
import com.example.social_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public Post addPost(@RequestBody Post post){
        User user1=userService.getOneUser(post.getUser().getId());
//        User user1=userRepository.getById(id);

        user1.setNumber_post(user1.getNumber_post()+1);
        userService.updateUser(post.getUser().getId(),user1);
        return postService.addPost(post);
    }

    @GetMapping("/list/{userId}")
    public List<Post> listPost(@PathVariable("userId") String userId){
        return postService.listPost(userId);
    }

    @PutMapping("/update/{id_post}")
    public Post updatePost(@RequestBody Post post, @PathVariable("id_post") String id_post){
        return postService.updatePost(id_post,post);
    }

    @DeleteMapping("/delete/{id_post}")
    public Post deletePost(@PathVariable("id_post") String id_post){
        User user1=userService.getOneUser(postRepository.getById(id_post).getUser().getId());
//        User user1=userRepository.getById(id);

        user1.setNumber_post(user1.getNumber_post()-1);
        userService.updateUser(postRepository.getById(id_post).getUser().getId(),user1);
        return postService.deletePost(id_post);
    }

    @GetMapping("/all")
    public List<Post> getAllPost(){
        return postService.getAllPost();
    }


}
