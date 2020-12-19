package com.example.lesson1.requestHistory;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class DataRequestHistory extends Application {
    static List<CityParcel> citiesList=new ArrayList<>();

    public List<CityParcel> getCitiesList() {
        return citiesList;
    }



}
