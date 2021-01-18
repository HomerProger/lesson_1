package com.example.lesson1;

import android.app.Application;

import com.example.lesson1.Parcel;
import com.example.lesson1.requestHistory.CityParcel;

import java.util.ArrayList;
import java.util.List;

public class AppClass extends Application {
    static List<CityParcel> citiesList = new ArrayList<>();
    public static Parcel parcel=new Parcel();
    public List<CityParcel> getCitiesList() {
        return citiesList;
    }


}
