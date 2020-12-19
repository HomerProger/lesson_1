package com.example.lesson1;

import android.widget.AutoCompleteTextView;

import com.example.lesson1.model.WeatherRequest;
import com.google.gson.Gson;

public class DataWeather {

    private String cityName;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private static double CHANGE_TO_MM_RTUT = 0.750063755419211;


    /**
     * @param result               - полученная с сервера информация о погоде в формате JSON
     * @param autoCompleteTextView - из этого параметра получаем значение города для отображения
     */
    public Parcel initData(String result, AutoCompleteTextView autoCompleteTextView) {
        WeatherRequest weatherRequest = getWeatherRequest(result);

        cityName = firstUpperCase(autoCompleteTextView.getText().toString());
        temperature = String.format("%.0f", weatherRequest.getMain().getTemp() - 273);
        pressure = String.format("%.0f", weatherRequest.getMain().getPressure() * CHANGE_TO_MM_RTUT);
        humidity = String.format("%d", weatherRequest.getMain().getHumidity());
        windSpeed = String.format("%.1f", weatherRequest.getWind().getSpeed());
        return new Parcel(cityName, temperature, pressure, humidity, windSpeed);
    }

    private WeatherRequest getWeatherRequest(String result) {
        Gson gson = new Gson();
        final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
        return weatherRequest;
    }

    public String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return ""; //или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}
