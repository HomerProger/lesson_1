package com.example.lesson1;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;

public class CitySelection extends Activity implements Constants {
    final String LOG_TAG = "myLogs";
    static final String KEY_CHECK_BOX1 = "KEY_CHECK_BOX1";
    static final String KEY_CHECK_BOX2 = "KEY_CHECK_BOX2";
    static final String KEY_CHECK_BOX3 = "KEY_CHECK_BOX3";
    boolean check_box_state1;
    boolean check_box_state2;
    boolean check_box_state3;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;

    Parcel parcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_city);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        String[] cities = getResources().getStringArray(R.array.cities);
        List<String> citiesList = Arrays.asList(cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, citiesList);
        autoCompleteTextView.setAdapter(adapter);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {


            parcel=new Parcel();
            checkBox1= findViewById(R.id.precipitation);
            checkBox2=findViewById(R.id.pressure);
            checkBox3=findViewById(R.id.wind);
            parcel.cityName= autoCompleteTextView.getText().toString();
            parcel.precipitationMark=checkBox1.isChecked();
            parcel.pressureMark=checkBox2.isChecked();
            parcel.windMark=checkBox3.isChecked();

            Intent intentResult = new Intent();
                    intentResult.putExtra(KEY, parcel);
                    setResult(RESULT_OK, intentResult);
                    finish();
                }
        );
        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "CitySelection Первый запуск!";
        } else {
            instanceState = "CitySelection Повторный запуск!";
            check_box_state1 = savedInstanceState.getBoolean(KEY_CHECK_BOX1);
            check_box_state2 = savedInstanceState.getBoolean(KEY_CHECK_BOX2);
            check_box_state3=savedInstanceState.getBoolean(KEY_CHECK_BOX3);
            checkBox1.setChecked(check_box_state1);
            checkBox2.setChecked(check_box_state2);
            checkBox3.setChecked(check_box_state3);
        }

        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, instanceState + "CitySelection_onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "CitySelection onStart()", Toast.LENGTH_SHORT).show();

        Log.d(LOG_TAG, "CitySelection_onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "CitySelection Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "CitySelection_onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "CitySelection onResume()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "CitySelection_onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "CitySelection onPause()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "CitySelection_onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putBoolean(KEY_CHECK_BOX1, checkBox1.isChecked());
        saveInstanceState.putBoolean(KEY_CHECK_BOX2, checkBox2.isChecked());
        saveInstanceState.putBoolean(KEY_CHECK_BOX3, checkBox2.isChecked());
        Toast.makeText(getApplicationContext(), "CitySelection onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "CitySelection_onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "CitySelection onStop()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "CitySelection_onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "CitySelection onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "CitySelection_onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "CitySelection onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "CitySelection_onDestroy");
    }
}
