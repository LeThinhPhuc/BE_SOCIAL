package com.example.social_be.service;

import com.example.social_be.model.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);

    public User updateUser(String id, User user);

    public List<User> deleteUser(String id);

    public List<User> getAllUser();

    public User getOneUser(String id);

    public User checkAuth(User user);
}
