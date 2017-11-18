package com.android.appliation.intermediate_iak;

/**
 * Created by adesanto on 11/11/17.
 */

public class Weather {
    private int weatherImage;
    private String weatherDate;
    private String weatherDesc;
    private String weatherTemperature;

    public Weather(int weatherImage, String weatherDate, String weatherDesc, String weatherTemperature) {
        this.weatherImage = weatherImage;
        this.weatherDate = weatherDate;
        this.weatherDesc = weatherDesc;
        this.weatherTemperature = weatherTemperature;
    }

    public int getWeatherImage() {
        return weatherImage;
    }

    public String getWeatherDate() {
        return weatherDate;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public String getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherImage(int weatherImage) {
        this.weatherImage = weatherImage;
    }

    public void setWeatherDate(String weatherDate) {
        this.weatherDate = weatherDate;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public void setWeatherTemperature(String weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }
}
