package com.example.Weather_Api.controller;

import com.example.Weather_Api.controller.ResponseDTO.WeatherResponseDTO;
import com.example.Weather_Api.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{city-name}")
    public ResponseEntity <List<WeatherResponseDTO>> getWeather(@PathVariable ("city-name") String city) throws JsonProcessingException {
        List <WeatherResponseDTO> weatherResponseDTOS=weatherService.getWeather(city);

        if (weatherResponseDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(weatherResponseDTOS, HttpStatus.OK);
    }

}
