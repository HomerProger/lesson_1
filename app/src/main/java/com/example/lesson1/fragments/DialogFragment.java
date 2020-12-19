package com.example.lesson1.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.lesson1.DataWeather;
import com.example.lesson1.Parcel;
import com.example.lesson1.R;
import com.example.lesson1.RequestHandler;
import com.example.lesson1.fragments.ShowWeatherFragment;

import java.util.Arrays;
import java.util.List;

import static com.example.lesson1.Constants.CURRENT_CITY;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    private RequestHandler requestHandler;
    private DataWeather data;
    private Parcel parcel;
    private AutoCompleteTextView autoCompleteTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog, null);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.imageButton).setOnClickListener(listener);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextViewD);
        setAdapter(autoCompleteTextView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            parcel = (Parcel) savedInstanceState.getSerializable(CURRENT_CITY);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            requestHandler = new RequestHandler(getContext());
            //Если введен пустой запрос, проинформируем об этом в диалоговом окне
            if (((AutoCompleteTextView) getView()
                    .findViewById(R.id.autoCompleteTextViewD))
                    .getText().toString().isEmpty()) {

                makeSimpleDialog(R.string.empty_request);
                return;
            } else {
                data = new DataWeather();
                requestHandler.initData(autoCompleteTextView);
                if (requestHandler.getResult() != null) {
                    parcel = data.initData(requestHandler.getResult(), autoCompleteTextView);
                    showWhether(parcel);
                }
            }
            autoCompleteTextView.setText(null);
            autoCompleteTextView.clearFocus();

        }
    };

    /**
     * Метод создания диалогового окан с сообщением и кнопкой "ОК"
     *
     * @param message ссылка на ресурсы string с сообщением для диалогового окна
     */
    private void makeSimpleDialog(int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.attention).setMessage(message)
                .setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

    private void setAdapter(AutoCompleteTextView autoCompleteTextView) {


        String[] cities = getResources().getStringArray(R.array.cities);
        List<String> citiesList = Arrays.asList(cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_dropdown_item_1line, citiesList);
        autoCompleteTextView.setAdapter(adapter);
    }


}