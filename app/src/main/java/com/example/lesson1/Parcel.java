package com.example.lesson1;

        import java.io.Serializable;


public class Parcel implements Serializable {
    private String cityName;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private String main;


    public String getCityName() {
        return cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getMain() {
        return main;
    }

    public void setCityName(String cityName) {

        this.cityName = firstUpperCase(cityName);
    }
    private String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return ""; //или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setMain(String s, String main) {
        this.main = main;
    }
}