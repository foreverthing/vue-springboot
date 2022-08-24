package com.example.springboot;

import com.example.springboot.entity.DTO.UserQueryDTO;
import com.example.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootApplicationTests {
 @Resource
    UserService service;

    @Test
    void contextLoads() {
        UserQueryDTO aa = new UserQueryDTO();
        aa.setEmail("zzuli");
        System.out.println(service.pageUser(aa));


    }

}
