package com.example.social_be.controller;


import com.example.social_be.model.User;
import com.example.social_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    public final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/")
    public String test(){ return "user thinh phuc";}

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user){
            if(userService.addUser(user)==null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bạn chưa điền đủ thông tin hoặc email đã tồn tại !");

            }else{
                return ResponseEntity.ok(userService.addUser(user));

            }


    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public List<User> deleteUser(@PathVariable("id") String id){
        return userService.deleteUser(id);
    }

    @GetMapping("/list")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") String id){
        try{
            User user=userService.getOneUser(id);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Co loi khi lay user");
        }
    }

//    @PostMapping("/login")
//    public  User loginUser(@RequestBody User user){
//        return userService.checkAuth(user);
//    }
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User authenticatedUser = userService.checkAuth(user);
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            // Trả về mã trạng thái 401 (Unauthorized) nếu xác thực thất bại
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
