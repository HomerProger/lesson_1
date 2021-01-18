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
    private static double CHANGE_TO_CELSIY=273.15;
    private static double CHANGE_TO_MM_RTUT = 0.750063755419211;


    /**
     * @param weatherRequest- данные о погоде от retrofit
     */
    public void initData(WeatherRequest weatherRequest) {

        //       cityName = firstUpperCase(autoCompleteTextView.getText().toString());
        AppClass.parcel.setTemperature(String.format("%.0f", weatherRequest.getMain().getTemp() - CHANGE_TO_CELSIY));
        AppClass.parcel.setPressure(String.format("%.0f", weatherRequest.getMain().getPressure() * CHANGE_TO_MM_RTUT));
        AppClass.parcel.setHumidity(String.format("%d", weatherRequest.getMain().getHumidity()));
        AppClass.parcel.setWindSpeed(String.format("%.1f", weatherRequest.getWind().getSpeed()));

    }

//    public String firstUpperCase(String word) {
//        if (word == null || word.isEmpty()) return ""; //или return word;
//        return word.substring(0, 1).toUpperCase() + word.substring(1);
//    }

}
