package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MainController {

    @GetMapping("/{msg}")
    public String hello(@PathVariable String msg){
        return "hello" +msg;
    }
}
