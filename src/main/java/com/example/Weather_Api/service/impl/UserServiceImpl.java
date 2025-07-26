package com.example.Weather_Api.service.impl;

import com.example.Weather_Api.model.Users;
import com.example.Weather_Api.repository.UserRepo;
import com.example.Weather_Api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Override
    public Users register(Users users) {

        users.setPassword(encoder.encode(users.getPassword()));

        repo.save(users);
        return users;
    }
}
