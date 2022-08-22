package com.example.springboot.service;

import com.example.springboot.entity.User;

import java.util.List;


public interface UserService {
    User getUser(int id);
    User login(String username);
    List<User> getAllUserData();
    void addUser(User user);
    void editUser(User user);
    void deleteUser(int id);
}
