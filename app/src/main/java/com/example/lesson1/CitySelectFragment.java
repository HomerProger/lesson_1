package com.example.lesson1;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lesson1.model.WeatherRequest;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class CitySelectFragment extends Fragment implements Constants {

    Parcel parcel;
    private String cityName;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        return inflater.inflate(R.layout.city_selection_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            parcel = (Parcel) savedInstanceState.getSerializable(CURRENT_CITY);
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

        if (saveInstanceState == null) {
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
            try {
                System.out.println("Мы здесь");
                final String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s",
                        ((AutoCompleteTextView) getView().findViewById(R.id.autoCompleteTextView2)).getText().toString(),
                        "cbceaf692f189faeab901270f83bea8f");
                final URL uri = new URL(url);
//                final Handler handler = new Handler(); // Запоминаем основной поток
                new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void run() {
                        HttpsURLConnection urlConnection = null;
                        try {
                            urlConnection = (HttpsURLConnection) uri.openConnection();
                            urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                            urlConnection.setReadTimeout(10000); // установка таймаута - 10 000 миллисекунд
                            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                            String result = getLines(in);
                            // преобразование данных запроса в модель
                            Gson gson = new Gson();
                            final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                            System.out.println("температура в лондоне "+weatherRequest.getMain().getTemp());

                            displayWeather(weatherRequest);
                           showWhether(parcel);
                            // Возвращаемся к основному потоку
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    displayWeather(weatherRequest);
//                                }
//                            });
                        } catch (Exception e) {
                            Log.e(TAG, "Fail connection", e);
                            e.printStackTrace();
                        } finally {
                            if (null != urlConnection) {
                                urlConnection.disconnect();
                            }
                        }
                    }
                }).start();
            } catch (MalformedURLException e) {
                Log.e(TAG, "Fail URI", e);
                e.printStackTrace();
            }
            autoCompleteTextView.setText(null);
            autoCompleteTextView.clearFocus();
//            showWhether(parcel);
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines (BufferedReader in){
        return in.lines().collect(Collectors.joining("\n"));
    }

    private void displayWeather(WeatherRequest weatherRequest){

        cityName=weatherRequest.getName();
        temperature =String.format("%.0f", weatherRequest.getMain().getTemp()-273);
        pressure=String.format("%d", weatherRequest.getMain().getPressure());
        humidity=String.format("%d", weatherRequest.getMain().getHumidity());
        windSpeed=String.format("%.0f", weatherRequest.getWind().getSpeed());

        parcel=new Parcel(cityName,temperature,pressure,humidity,windSpeed);

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
