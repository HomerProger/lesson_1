package com.example.lesson1;

import com.example.lesson1.model.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeather {
@GET("data/2.5/weather")
    Call<WeatherRequest>loadWeather(@Query("q") String city,@Query("appid") String keyApi);

}
