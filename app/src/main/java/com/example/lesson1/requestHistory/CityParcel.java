package com.example.lesson1.requestHistory;

public class CityParcel {
    String city;
    String date;
    String time;
    String temp;

    public CityParcel(String city, String date, String time, String temp) {
        this.city = city;
        this.date = date;
        this.time = time;
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTemp() {
        return temp;
    }
}
