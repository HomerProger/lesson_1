package com.example.lesson1.menuToolbar;

import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import com.example.lesson1.BaseActivity;
import com.example.lesson1.R;

public class SettingsActivity extends BaseActivity {
private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SwitchCompat switchCompat=findViewById(R.id.switch1);
        switchCompat.setChecked(isDarkTheme());
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setDarkTheme(isChecked);
            recreate();
        });
    }
}
