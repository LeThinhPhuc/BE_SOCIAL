package com.example.social_be.controller;

import com.example.social_be.model.Post;
import com.example.social_be.model.User;
import com.example.social_be.repository.PostRepository;
import com.example.social_be.repository.UserRepository;
import com.example.social_be.service.PostService;
import com.example.social_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Post addPost(@RequestBody Post post) {
        try {
            User user = userService.getOneUser(post.getUser().getId());

            user.setNumber_post(user.getNumber_post() + 1);
            userService.updateUser(post.getUser().getId(), user);
            System.out.println("Sắp add");
            return postService.addPost(post);
        }catch (Exception e) {
            System.out.println("Lỗi rồi");
            return null;
        }
    }

    @GetMapping("/list/{userId}")
    public List<Post> listPost(@PathVariable("userId") String userId) {
        return postService.listPost(userId);
    }

    @GetMapping("/all")
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }

    @DeleteMapping("/delete/{id_post}")
    public boolean deletePost(@PathVariable("id_post") String id_post) {
        try {
            Post post = postRepository.getById(id_post);
            User user = userService.getOneUser(post.getUser().getId());

            user.setNumber_post(user.getNumber_post() - 1);
            userService.updateUser(user.getId(), user);

            return postService.deletePost(id_post);
        } catch (Exception e) {
            return false;
        }
    }

    @PutMapping("/update/{id_post}")
    public Post updatePost(@RequestBody Post post, @PathVariable("id_post") String id_post) {
        return postService.updatePost(id_post, post);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") String id){
        try{
            Post post = postService.getPost(id);
            return ResponseEntity.ok(post);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Co loi khi lay post");
        }
    }

    // http://localhost:8080/post/like?id_post=1&userId=3
    @PostMapping("/like")
    public ResponseEntity<?> likePost(@RequestParam(value = "id_post", defaultValue = "-1") String id_post,
                                      @RequestParam(value = "userId", defaultValue = "-1") String userId) {
        try {
            Post post = postService.getPost(id_post);
            User user = userService.getOneUser(userId);

            if (post == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bài viết không tồn tại");
            }

            if (post.getLikedUsers().contains(userId)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng đã like bài viết này rồi");
            }

            if(user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Người dùng không tồn tại");
            }

            String seperate = post.getLikedUsers().equals("") ? "" : ";";
            String name = post.getLikedUsers() + seperate + user.getUsername();

            post.setLikedUsers(name);
            post.setLike(post.getLike() + 1);

            Post updated =  postService.updatePost(id_post, post);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thực hiện like bài viết");
        }
    }


    // http://localhost:8080/post/dislike?id_post=1&userId=3
    @PostMapping("/dislike")
    public ResponseEntity<?> dislikePost(@RequestParam(value = "id_post", defaultValue = "-1") String id_post,
                                         @RequestParam(value = "userId", defaultValue = "-1") String userId) {
        try {
            Post post = postService.getPost(id_post);
            User user = userService.getOneUser(userId);

            if (post == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bài viết không tồn tại");
            }

            if (!post.getLikedUsers().contains(userId)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng chưa like bài viết này");
            }

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Người dùng không tồn tại");
            }

            String likedUsers = post.getLikedUsers();
//            likedUsers = likedUsers.replace(user.getUsername() + ";", "");
            likedUsers = likedUsers.replaceAll(user.getUsername() + "(;|$)", "");

            if(likedUsers.endsWith(";")) {
                likedUsers = likedUsers.substring(0,likedUsers.length()-1);
            }

//            int index = likedUsers.indexOf(user.getUsername());
//            likedUsers =  likedUsers.substring(0, index-1) +
//                    likedUsers.substring(index+user.getUsername().length());

            // user1;user2;user3

            post.setLikedUsers(likedUsers);
            post.setLike(post.getLike() - 1);

            Post updated = postService.updatePost(id_post, post);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thực hiện dislike bài viết");
        }
    }
}
