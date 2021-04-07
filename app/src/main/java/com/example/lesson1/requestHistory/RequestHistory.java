package com.example.lesson1.requestHistory;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson1.AppClass;
import com.example.lesson1.BaseActivity;
import com.example.lesson1.Constants;
import com.example.lesson1.R;

import java.util.List;

public class RequestHistory extends BaseActivity implements Constants {
    private Toolbar toolbar;
    private RequestHistoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_history);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initList();
    }

    private void initList() {
        RecyclerView recyclerView = findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RequestHistoryAdapter(initData(), this);
        recyclerView.setAdapter(adapter);
    }

    private List<CityParcel> initData() {
        AppClass appClass = (AppClass) getApplication();
        return appClass.getCitiesList();

    }


}
