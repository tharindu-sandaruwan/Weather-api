package com.example.Weather_Api.controller;

import com.example.Weather_Api.model.Users;
import com.example.Weather_Api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return userService.register(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody Users users){



        return userService.verify(users);
    }

}
