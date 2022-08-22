package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.entity.resp.RestBean;
import com.example.springboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class MainController {
@Resource
    UserService service;


    @GetMapping("/{id}")
    public User hello(@PathVariable int id){
       return service.getUser(id);

    }

    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public RestBean<Void> login(@RequestBody User user) throws NullPointerException {
       User loginUser = service.login(user.getUsername());
        if(loginUser ==null){return new RestBean<>(404,"无此用户名，请注册");}
        if (Objects.equals(loginUser.getPassword(), user.getPassword())
        ) {
            return new RestBean<>(200,"登入成功");
        }else {
           return new RestBean<>(401,"用户名或密码错误");
        }
    }

    @GetMapping
    public RestBean<List<User>> getUserData(){
           List<User> allUser= service.getAllUserData();
        return new RestBean<>(200, "获取成功", allUser);
    }

    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    public  RestBean<Void> addUser(@RequestBody User user){
       if (user!=null){service.addUser(user);
       return new RestBean<>(200,"添加成功");
       }else {return new RestBean<>(400,"添加失败");}

    }

    @RequestMapping(value = "/editUser" ,method = RequestMethod.POST)
    public  RestBean<Void> editUser(@RequestBody User user){

        if (service.getUser(user.getId())!=null){
            service.editUser(user);
            return new RestBean<>(200,"编辑成功");
        }else {return new RestBean<>(404,"编辑失败,用户不存在!");}
    }


    @RequestMapping(value = "/deleteUser/{id}" ,method = RequestMethod.POST)
    public  RestBean<Void> deleteUser(@PathVariable int id){

        service.deleteUser(id);
        return new RestBean<>(200,"删除成功");
    }
}
