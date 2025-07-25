package com.example.Weather_Api.service.impl;

import com.example.Weather_Api.controller.ResponseDTO.WeatherResponseDTO;
import com.example.Weather_Api.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final String API_URL="https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<WeatherResponseDTO> getWeather(String city) throws JsonProcessingException {

        Map <String,String> uriVariables=new HashMap<>();
        uriVariables.put("city",city);
        uriVariables.put("apiKey","8227c35669ce721137a2efbdf541de8f");

        String response =restTemplate.getForObject(API_URL, String.class,uriVariables);


        JsonNode root =objectMapper.readTree(response);

        String cityName=root.path("name").asText();
        String temperature=root.path("main").path("temp").asText();
        String humidity=root.path("main").path("humidity").asText();
        String pressure=root.path("main").path("pressure").asText();

        WeatherResponseDTO weatherResponseDTO=new WeatherResponseDTO();

        weatherResponseDTO.setCity(cityName);
        weatherResponseDTO.setTemperature(temperature);
        weatherResponseDTO.setHumidity(humidity);
        weatherResponseDTO.setPressure(pressure);

        return List.of(weatherResponseDTO);
    }
}
