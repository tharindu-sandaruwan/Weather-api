package com.example.Weather_Api.service;

import com.example.Weather_Api.controller.ResponseDTO.WeatherResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface WeatherService {

    List <WeatherResponseDTO> getWeather(String city) throws JsonProcessingException;

}
