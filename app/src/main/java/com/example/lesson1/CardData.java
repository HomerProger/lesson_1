package com.example.lesson1;

public class CardData {
    private String date; // дата
    private String dayOfWeek;        // день недели
    private String temperature; // температура
    private int picture; // картинка погоды
    private String precipitation; // осадки

    public CardData(String date, String dayOfWeek, String temperature, int picture, String precipitation) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.temperature = temperature;
        this.picture = picture;
        this.precipitation = precipitation;
    }


    // геттеры


    public String getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getTemperature() {
        return temperature;
    }

    public int getPicture() {
        return picture;
    }

    public String getPrecipitation() {
        return precipitation;
    }
}
