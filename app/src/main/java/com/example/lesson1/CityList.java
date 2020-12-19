package com.example.lesson1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lesson1.fragments.ShowWeatherFragment;

import static android.view.Gravity.CENTER;

public class CityList extends Fragment {
Parcel parcel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        return inflater.inflate(R.layout.list_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle saveInstanceState) {

        super.onViewCreated(view, saveInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout linearLayout=(LinearLayout)view;
String[] cities=getResources().getStringArray(R.array.cities);
        for (int i = 0; i <cities.length ; i++) {
            String city =cities[i];
            TextView tv=new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(20);
            tv.setGravity(CENTER);
            linearLayout.addView(tv);
            final int index=i;
            tv.setOnClickListener((v )->{
parcel=new Parcel(getResources().getStringArray(R.array.cities)[index]);
showWhether(parcel);

            });


        }

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
