package com.example.lesson1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
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

        if (getArguments()!=null){
            return (Parcel)getArguments().getSerializable(PARCEL);
        }else {
            return new Parcel(getResources().getStringArray(R.array.cities)[0]);
        }

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_whether_fragment,container,false);
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

        if(savedInstanceState!=null){
           parcel = (Parcel)savedInstanceState.getSerializable("KEY");
        }else {
            parcel = getParcel();
        }
        TextView cityName = view.findViewById(R.id.cityName);
        cityName.setText(parcel.getCityName());
        cityName.setTextSize(30);
    }



}
