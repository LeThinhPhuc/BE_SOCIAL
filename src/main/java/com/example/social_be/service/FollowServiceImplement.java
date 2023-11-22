package com.example.social_be.service;

import com.example.social_be.model.Follow;
import com.example.social_be.model.User;
import com.example.social_be.repository.FollowRepository;
import com.example.social_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowServiceImplement implements FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Override
    public boolean follow(Follow follow) {
        Optional<Follow> existingFollow = followRepository.findById(follow.getId());

        if (existingFollow.isPresent() && existingFollow.get().equals(follow)) {
            return false;
        }

        followRepository.save(follow);
        return true;
    }

    @Override
    public boolean unfollow(Follow follow) {
        followRepository.delete(follow);
        return true;
    }

    @Override
    public List<Follow> getFollowing(User user) {
        return followRepository.findFollowByUsers(user.getId());
    }

    @Override
    public List<Follow> getFollowers(User user) {
        return followRepository.findFollowsByFollowed(user.getId());
    }


    @Override
    public List<Follow> getAllFollow(){
        return followRepository.findAll();
    }


}
