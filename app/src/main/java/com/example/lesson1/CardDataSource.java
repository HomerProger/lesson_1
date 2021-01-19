package com.example.lesson1;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class CardDataSource implements DataSource {

    private List<CardData> dataSource; // источник данных
    private Resources resources; // ресурсы приложения

    public CardDataSource(Resources resources) {
        dataSource = new ArrayList<>(11);
        this.resources = resources;
    }

    public CardDataSource init() {
        String[] dates = resources.getStringArray(R.array.dates);
        String[] dayOfWeek = resources.getStringArray(R.array.days);
        String[] temperature = resources.getStringArray(R.array.temperature);
        String[] precipitation=resources.getStringArray(R.array.precipitation);
        int[] pictures = getImageArray();
        for (int i=0; i<dates.length;i++){
            dataSource.add(new CardData(dates[i],dayOfWeek[i],temperature[i],pictures[i], precipitation[i]));
        }
    return this;
    }

    private int[] getImageArray() {
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        int length = pictures.length();
        int[] answer = new int[length];
        for(int i = 0; i < length; i++){
            answer[i] = pictures.getResourceId(i, 0);
        }
        return answer;
    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }
}
