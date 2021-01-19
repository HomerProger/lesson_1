package com.example.lesson1;

        import java.io.Serializable;


public class Parcel implements Serializable {
    private String cityName;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;

    public Parcel(String cityName, String temperature, String pressure, String humidity, String windSpeed) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }
    public Parcel(String cityName) {
        this.cityName = cityName;
    }
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

}
