package com.example.lesson1;

import android.content.res.Resources;

public class CardDataSourceBuilder {

    private Resources resources;

    public CardDataSourceBuilder setResources(Resources resources){
        this.resources=resources;
        return this;
    }
public DataSource build(){

        CardDataSource cardDataSource=new CardDataSource(resources);
        cardDataSource.init();
        return cardDataSource;
}
}
