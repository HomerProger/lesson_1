package com.example.lesson1;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.lesson1.fragments.ShowWeatherFragment;
import com.example.lesson1.menuToolbar.AboutActivity;
import com.example.lesson1.menuToolbar.SettingsActivity;
import com.example.lesson1.requestHistory.RequestHistory;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    public static LinearLayout mainLL;
    //Код для возвращение результата настроек  темы на главный экран
    private static final int SETTING_CODE = 88;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        mainLL=findViewById(R.id.mainLinearLayout);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);

        if (savedInstanceState == null) {
            AppClass.parcel.setCityName(getResources().getStringArray(R.array.cities)[0]);
            showWhether(AppClass.parcel);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Управление касаниями на action bar.
        // Action bar будет автоматически управлять нажатиями на Home/Up кнопку
        // Вы это можете указать в родительском активити в файле манифеста.
        int id = item.getItemId();

        switch (id) {
            case R.id.settings:
                Snackbar.make(toolbar, "Вы выбрали пункт меню Настройки", Snackbar.LENGTH_LONG).show();
                Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(intentSettings, SETTING_CODE);
                return true;
            case R.id.about:
                Snackbar.make(toolbar, "Вы выбрали пункт меню О программе", Snackbar.LENGTH_LONG).show();
                Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE) {
            recreate();
        }

    }

    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                // TODO:
                break;
            case R.id.nav_history:
                Intent intentHistory = new Intent(MainActivity.this, RequestHistory.class);
                startActivity(intentHistory);
                break;
            case R.id.nav_favorites:
                break;
            case R.id.nav_about:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void showWhether(Parcel parcel) {
        ShowWeatherFragment showWeatherFragment = ShowWeatherFragment.create(parcel);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_show, showWeatherFragment) // замена фрагмента
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }
}
