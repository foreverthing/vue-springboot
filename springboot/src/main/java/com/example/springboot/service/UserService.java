package com.example.springboot.service;

import com.example.springboot.entity.DTO.UserQueryDTO;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUser(int id);
    User login(String username);
    List<User> getAllUserData();
    void addUser(User user);
    void editUser(User user);
    void deleteUser(int id);
    List<User> pageUser(UserQueryDTO userQueryDTO);
}
