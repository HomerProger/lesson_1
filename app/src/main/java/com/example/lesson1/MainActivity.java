package com.example.lesson1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements Constants {
    final String LOG_TAG = "myLogs";
    private final static int REQUEST_CODE = 34;
Parcel parcel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button1);

        button.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, CitySelection.class);
                    //startActivity(intent);
                    startActivityForResult(intent, REQUEST_CODE);
                }
        );
        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "MainActivity Первый запуск!";
        } else {
            instanceState = "MainActivity Повторный запуск!";
        }

        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, instanceState + "onCreate");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK) {

            Parcel parcel;
            parcel = (Parcel) data.getExtras().getSerializable(KEY);
            TextView cityName = findViewById(R.id.textView1);
            LinearLayout linearLayout1 = findViewById(R.id.PrecipitationLinearLayout);
            LinearLayout linearLayout2 = findViewById(R.id.PressureLinearLayout);
            LinearLayout linearLayout3 = findViewById(R.id.WindLinearLayout);


            cityName.setText(parcel.cityName);
            if(!parcel.precipitationMark){
                linearLayout1.setVisibility(View.INVISIBLE);
            }else {
                linearLayout1.setVisibility(View.VISIBLE);
            }
            if(!parcel.pressureMark){
                linearLayout2.setVisibility(View.INVISIBLE);
            }else {
                linearLayout2.setVisibility(View.VISIBLE);
            }
            if(!parcel.windMark){
                linearLayout3.setVisibility(View.INVISIBLE);
            }else {
                linearLayout3.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "MainActivity onStart()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "MainActivity Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "MainActivity onResume()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "MainActivity onPause()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "MainActivity onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "MainActivity onStop()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "MainActivity onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "MainActivity onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onDestroy");
    }

}
