package com.example.lesson1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson1.recyclerView.CardDataApadter;
import com.example.lesson1.recyclerView.CardDataSourceBuilder;
import com.example.lesson1.recyclerView.DataSource;

public class ShowWeatherFragment extends Fragment {

    public static final String PARCEL = "parcel";

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

        if (savedInstanceState != null) {
            parcel = (Parcel) savedInstanceState.getSerializable("KEY");
        } else {
            parcel = getParcel();
        }
        TextView cityName = view.findViewById(R.id.cityName);
        TextView temperature = view.findViewById(R.id.temperature);
        TextView pressure = view.findViewById(R.id.pressure);
        TextView humidity = view.findViewById(R.id.humidity);
        TextView wind = view.findViewById(R.id.wind);

        cityName.setText(parcel.getCityName());
        temperature.setText(parcel.getTemperature());
        pressure.setText(parcel.getPressure());
        humidity.setText(parcel.getHumidity());
        wind.setText(parcel.getWindSpeed());
        cityName.setTextSize(30);

        initDataSource();
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
