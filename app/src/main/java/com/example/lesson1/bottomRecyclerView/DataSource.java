package com.example.lesson1.bottomRecyclerView;

public interface DataSource {
    CardData getCardData(int position);
    int size();

}
