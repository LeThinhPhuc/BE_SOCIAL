package com.example.social_be.controller;


import com.example.social_be.model.Follow;
import com.example.social_be.model.User;
import com.example.social_be.service.FollowService;
import com.example.social_be.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/f")
public class FollowController {
    @Autowired
    private FollowService followService;
    public UserService userService;

    @Autowired
    public FollowController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }


    //! http://localhost:8080/f/follow?userId=1&followedId=3
    @PostMapping("/follow")
    public boolean follow(@RequestParam(value = "userId", defaultValue = "-1") String userId,
                          @RequestParam(value = "followedId", defaultValue = "-1") String followedId) {
        if (userId.equals("-1") || followedId.equals("-1")) return false;
        if(userId.equals(followedId)) return false;

        try {
            User user = new User(userService.getOneUser(userId)),
                    followed = new User(userService.getOneUser(followedId));

            Follow follow = new Follow(user, followed);
            followService.follow(follow);

            user.setNumber_followed(user.getNumber_followed() + 1);
            userService.updateUser(userId, user);

            followed.setNumber_following(followed.getNumber_following() + 1);
            userService.updateUser(followedId, followed);
            System.out.println(user);
            System.out.println(followed);


            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    //! http://localhost:8080/f/unfollow?userId=1&followedId=3
    @DeleteMapping("/unfollow")
    public boolean unfollow(@RequestParam(value = "userId", defaultValue = "-1") String userId,
                            @RequestParam(value = "followedId", defaultValue = "-1") String followedId) {
        if (userId.equals("-1") || followedId.equals("-1")) return false;
        if(userId.equals(followedId)) return false;

        try {
            User user = new User(userService.getOneUser(userId)),
                    followed = new User(userService.getOneUser(followedId));

            Follow follow = new Follow(user, followed);
            followService.unfollow(follow);

            user.setNumber_followed(user.getNumber_followed() - 1);
            userService.updateUser(userId, user);

            followed.setNumber_following(followed.getNumber_following() - 1);
            userService.updateUser(followedId, followed);

            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    @GetMapping("/getFollowing")
    public List<Follow> getFollowings(@RequestParam(value = "userId", defaultValue = "-1") String userId) {
        try {
            User user = new User(userService.getOneUser(userId));

            return followService.getFollowing(user);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @GetMapping("/getFollowers")
    public List<Follow> getFollowers(@RequestParam(value = "followedId", defaultValue = "-1") String followedId) {
        try {
            User user = new User(userService.getOneUser(followedId));

            return followService.getFollowers(user);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }


    @GetMapping("/all")
    public List<Follow> getAllFollow(){
        return followService.getAllFollow();
    }


}
