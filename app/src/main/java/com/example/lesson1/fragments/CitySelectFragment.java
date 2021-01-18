package com.example.lesson1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.lesson1.Constants;
import com.example.lesson1.R;

public class CitySelectFragment extends Fragment implements Constants {

DialogFragment dlgFragment;
static FragmentManager fragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dlgFragment=new DialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        return inflater.inflate(R.layout.city_selection_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, Bundle saveInstanceState) {

        super.onViewCreated(view, saveInstanceState);
fragmentManager=getFragmentManager();
        ImageButton buttonGo = view.findViewById(R.id.button3);
        buttonGo.setOnClickListener((v) -> {
            dlgFragment.show(getFragmentManager(),"dialogFragment");
        });

    }

}
