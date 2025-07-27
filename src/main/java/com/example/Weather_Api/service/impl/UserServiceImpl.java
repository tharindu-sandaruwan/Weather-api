package com.example.Weather_Api.service.impl;

import com.example.Weather_Api.model.Users;
import com.example.Weather_Api.repository.UserRepo;
import com.example.Weather_Api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Override
    public Users register(Users users) {

        users.setPassword(encoder.encode(users.getPassword()));

        repo.save(users);
        return users;
    }

    @Override
    public String verify(Users users) {
        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(users.getUsername());
        return "Fail";
    }
}
