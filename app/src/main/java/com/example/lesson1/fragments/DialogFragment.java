package com.example.lesson1.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.lesson1.BuildConfig;
import com.example.lesson1.DataWeather;
import com.example.lesson1.MainActivity;
import com.example.lesson1.OpenWeather;
import com.example.lesson1.Parcel;
import com.example.lesson1.R;
import com.example.lesson1.model.WeatherRequest;
import com.example.lesson1.AppClass;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static com.example.lesson1.Constants.CURRENT_CITY;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    private DataWeather data;
    private AutoCompleteTextView autoCompleteTextView;
    private OpenWeather openWeather;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dialog, null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextViewD);
        setAdapter(autoCompleteTextView);
        view.findViewById(R.id.imageButton).setOnClickListener(listener);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            AppClass.parcel = (Parcel) savedInstanceState.getSerializable(CURRENT_CITY);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = getContext();
            dismiss();
            //Если введен пустой запрос, проинформируем об этом в диалоговом окне
            if (autoCompleteTextView
                    .getText().toString().isEmpty()) {

                makeSimpleDialog(R.string.empty_request, context);
                return;
            } else {
                initRetrofit();
                requestRetrofit(autoCompleteTextView.getText().toString(), BuildConfig.WEATHER_API_KEY, autoCompleteTextView, context);
            }
        }
    };

    /**
     * Метод создания диалогового окан с сообщением и кнопкой "ОК"
     *
     * @param message ссылка на ресурсы string с сообщением для диалогового окна
     */
    private void makeSimpleDialog(int message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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

        CitySelectFragment.fragmentManager
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

    private void initRetrofit() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        openWeather = retrofit.create(OpenWeather.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    private void requestRetrofit(String city, String keyApi, AutoCompleteTextView autoCompleteTextView, Context context) {
        AppClass.parcel.setCityName(autoCompleteTextView.getText().toString());
        autoCompleteTextView.setText(null);
        autoCompleteTextView.clearFocus();
        Handler handler = new Handler();
        openWeather.loadWeather(city, keyApi)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {

                        if (response.body() != null) {
                            data = new DataWeather();
                            data.initData(response.body());
                            String backgroundLink;
                            switch (AppClass.parcel.getMain()){
                                case "Snow":
                                    backgroundLink="https://ic.wampi.ru/2021/03/17/snow.jpg";
                                    break;
                                case "Rain":
                                    backgroundLink="https://ic.wampi.ru/2021/04/07/rain_.jpg";
                                    break;
                                case "Mist":
                                    backgroundLink="https://ic.wampi.ru/2021/03/17/mist.png";
                                    break;
                                case "Clouds":
                                    backgroundLink="https://ic.wampi.ru/2021/03/17/clouds.jpg";
                                    break;
                                default:
                                    backgroundLink="https://ic.wampi.ru/2021/03/17/clear.png";

                            }


                            Picasso.get().load(backgroundLink).into(new Target() {
                                @Override
                                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                    MainActivity.mainLL.setBackground(new BitmapDrawable(bitmap));
                                }

                                @Override
                                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                }

                                @Override
                                public void onPrepareLoad(Drawable placeHolderDrawable) {

                                }
                            });
                            showWhether(AppClass.parcel);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        if (t instanceof FileNotFoundException) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    makeSimpleDialog(R.string.not_found_city, context);
                                }
                            });

                        }
                        if (t instanceof UnknownHostException) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    makeSimpleDialog(R.string.not_connection, context);
                                }
                            });

                        }
                        t.printStackTrace();
                    }

                });
    }

}