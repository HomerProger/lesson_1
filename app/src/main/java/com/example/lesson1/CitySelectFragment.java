package com.example.lesson1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;

import java.util.Arrays;
import java.util.List;

public class CitySelectFragment extends Fragment implements Constants {

    Parcel parcel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        return inflater.inflate(R.layout.city_selection_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            parcel = (Parcel)savedInstanceState.getSerializable(CURRENT_CITY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(CURRENT_CITY, parcel);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, Bundle saveInstanceState) {

        super.onViewCreated(view, saveInstanceState);

        AutoCompleteTextView citySelect = view.findViewById(R.id.autoCompleteTextView2);
        MaterialButton buttonGo = view.findViewById(R.id.button3);

if(saveInstanceState==null){
    showWhether(new Parcel(getResources().getStringArray(R.array.cities)[0]));
}
        initCitySelect(view, citySelect, buttonGo);

    }

    private void initCitySelect(View view, AutoCompleteTextView autoCompleteTextView, Button button) {


        String[] cities = getResources().getStringArray(R.array.cities);
        List<String> citiesList = Arrays.asList(cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_dropdown_item_1line, citiesList);
        autoCompleteTextView.setAdapter(adapter);

        button.setOnClickListener((v) -> {
            parcel = new Parcel(autoCompleteTextView.getText().toString());
            autoCompleteTextView.setText(null);
            autoCompleteTextView.clearFocus();
            showWhether(parcel);
        });
    }

    private void showWhether(Parcel parcel) {
        ShowWeatherFragment showWeatherFragment = ShowWeatherFragment.create(parcel);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_show, showWeatherFragment) // замена фрагмента
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

}
