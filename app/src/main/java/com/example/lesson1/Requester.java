package com.example.lesson1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import androidx.annotation.RequiresApi;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class Requester {
    Thread t;
    private String result;
    Context context;

    public String getResult() {
        return result;
    }

    public Requester(Context context) {
        this.context = context;
    }

    public void initData(AutoCompleteTextView autoCompleteTextView) {
        try {
            final String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s",
                    autoCompleteTextView.getText().toString(), BuildConfig.WEATHER_API_KEY);
            final URL uri = new URL(url);
            final Handler handler = new Handler(); // Запоминаем основной поток

            t = new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {

                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                        urlConnection.setReadTimeout(10000); // установка таймаута - 10 000 миллисекунд
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                        result = getLines(in);
                    } catch (FileNotFoundException e) {
                        Log.e(TAG, "Поймали ошибку FileNotFoundException", e);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                makeSimpleDialog(R.string.not_found_city);
                            }
                        });
                        e.printStackTrace();
                    } catch (UnknownHostException e) {
                        Log.e(TAG, "Поймали ошибку UnknownHostException", e);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                makeSimpleDialog(R.string.not_connection);
                            }
                        });
                        e.printStackTrace();
                    } catch (Exception e) {
                        Log.e(TAG, "Fail connection", e);
                        e.printStackTrace();
                    } finally {
                        if (null != urlConnection) {
                            urlConnection.disconnect();
                        }
                    }
                }
            });


            t.start();
            t.join();
        } catch (
                MalformedURLException | InterruptedException e) {
            Log.e(TAG, "Fail URI", e);
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }


    private void makeSimpleDialog(int message) {
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
}
