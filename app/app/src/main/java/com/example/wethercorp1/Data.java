package com.example.wethercorp1;

public class Data {
    private String date;
    private Integer airPressure;
    private Integer humidity;
    private Integer temperature;
    private Integer luminosity;

    Data(String date, Integer temperature, Integer humidity, Integer airPressure, Integer luminosity){
        this.date = date;
        this.airPressure = airPressure;
        this.humidity = humidity;
        this.luminosity = luminosity;
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(Integer airPressure) {
        this.airPressure = airPressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(Integer luminosity) {
        this.luminosity = luminosity;
    }
}
