package com.example.lesson1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShowWeatherFragment extends Fragment {

    public static final String PARCEL="parcel";
    public static ShowWeatherFragment create(Parcel parcel){
        ShowWeatherFragment showWeatherFragment = new ShowWeatherFragment();

        Bundle args=new Bundle();
        args.putSerializable(PARCEL,parcel);
        showWeatherFragment.setArguments(args);
        return showWeatherFragment;
    }

    public Parcel getParcel(){

        Parcel parcel = (Parcel)getArguments().getSerializable(PARCEL);

//        Parcel parcel=new Parcel("khbsvfbjsfv");
        return parcel;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_whether_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView cityName = view.findViewById(R.id.cityName);
        Parcel parcel=getParcel();
        cityName.setText(parcel.getCityName());

    }



}
