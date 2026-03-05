package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.User;

import java.util.List;

public interface UserService {
    User getProfile(String email);
    User updateProfile(String email, User user);
    List<User> getAllUsers();
}
