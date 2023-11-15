package com.example.social_be.service;

import com.example.social_be.model.Follow;
import com.example.social_be.model.User;

import java.util.List;

public interface FollowService {
    public boolean follow(Follow follow);
    public boolean unfollow(Follow follow);
    public List<Follow> getFollowing(User user);
    public List<Follow> getFollowers(User user);
}
