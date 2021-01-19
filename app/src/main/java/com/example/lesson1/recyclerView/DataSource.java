package com.example.lesson1.recyclerView;

import com.example.lesson1.recyclerView.CardData;

public interface DataSource {
    CardData getCardData(int position);
    int size();

}
