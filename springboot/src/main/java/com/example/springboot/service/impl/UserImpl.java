package com.example.springboot.service.impl;

import com.example.springboot.entity.User;
import com.example.springboot.mapper.MainMapper;
import com.example.springboot.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserImpl implements UserService{
@Resource
    MainMapper mapper;

    @Override
    public User getUser(int id) {
      return   mapper.getUserById(id);

    }

    @Override
    public User login(String username) {
        return mapper.getPasswordByUsername(username);
    }

    @Override
    public List<User> getAllUserData() {
        return mapper.getAllUserData();
    }

    @Override
    public void addUser(User user) {
        mapper.addUser( user);
    }

    @Override
    public void editUser(User user) {
        mapper.editUser(user);
    }

    @Override
    public void deleteUser(int id) {
        mapper.deleteUser(id);
    }
}
