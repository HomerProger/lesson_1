package com.example.lesson1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson1.recyclerView.CardDataApadter;
import com.example.lesson1.recyclerView.CardDataSourceBuilder;
import com.example.lesson1.recyclerView.DataSource;
import com.example.lesson1.requestHistory.CityParcel;
import com.example.lesson1.requestHistory.DataRequestHistory;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ShowWeatherFragment extends Fragment {

    public static final String PARCEL = "parcel";
    CityParcel cityParcel;
   DataRequestHistory dataRequestHistory;

    public static ShowWeatherFragment create(Parcel parcel) {
        ShowWeatherFragment showWeatherFragment = new ShowWeatherFragment();

        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        showWeatherFragment.setArguments(args);
        return showWeatherFragment;
    }

    public Parcel getParcel() {

        return (Parcel) getArguments().getSerializable(PARCEL);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_whether_fragment, container, false);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("KEY", getParcel());
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Parcel parcel;

        TextView cityName = view.findViewById(R.id.cityName);
        TextView temperature = view.findViewById(R.id.temperature);
        TextView pressure = view.findViewById(R.id.pressure);
        TextView humidity = view.findViewById(R.id.humidity);
        TextView wind = view.findViewById(R.id.wind);


        if (savedInstanceState != null) {
            parcel = (Parcel) savedInstanceState.getSerializable("KEY");
            cityName.setText(parcel.getCityName());
        } else {
            parcel = getParcel();
            cityName.setText(parcel.getCityName());
            if(parcel.getTemperature()!=null) {
                dataRequestHistory=new DataRequestHistory();
                cityParcel=new CityParcel(parcel.getCityName(),getDate(),getTime(),parcel.getTemperature()+" "+getString(R.string.temp_mark));
               dataRequestHistory.getCitiesList().add(cityParcel);
                temperature.setText(parcel.getTemperature() + "  " + getString(R.string.temp_mark));
                temperature.setTextSize(26);
                pressure.setText(parcel.getPressure() + "  " + getString(R.string.pressure_mark));
                pressure.setTextSize(26);
                humidity.setText(parcel.getHumidity() + "  " + getString(R.string.humidity_mark));
                humidity.setTextSize(26);
                wind.setText(parcel.getWindSpeed() + "  " + getString(R.string.wind_mark));
                wind.setTextSize(26);
            }
            }



        initDataSource();
    }

    private String getTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return timeFormat.format(new Date());

    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    private void initDataSource() {
        // строим источник данных
        DataSource sourceData = new CardDataSourceBuilder()
                .setResources(getResources())
                .build();

        initRecyclerView(sourceData);

    }

    private void initRecyclerView(DataSource sourceData) {
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Добавим разделитель карточек
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL);
        itemDecoration.setDrawable(getActivity().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);


        // Установим адаптер
        CardDataApadter adapter = new CardDataApadter(sourceData);
        recyclerView.setAdapter(adapter);

    }


}
