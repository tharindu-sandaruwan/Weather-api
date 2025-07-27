package com.example.Weather_Api.service;

import com.example.Weather_Api.model.Users;

public interface UserService {

    Users register(Users users);

    String verify(Users users);
}
