package com.example.Weather_Api.controller.ResponseDTO;

public class WeatherResponseDTO {

    private String cityName;
    private String temperature;
    private String humidity;
    private String pressure;

    public String getCity() {
        return cityName;
    }

    public void setCity(String city) {
        this.cityName = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
