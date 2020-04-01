package com.example.lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        logSharedPreferences();
    }

    public void logSharedPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean first_checkbox = prefs.getBoolean("switch_preference_1", false);
        boolean second_checkbox = prefs.getBoolean("switch_preference_2", false);
        Log.d("Preference = ", String.valueOf(first_checkbox));
        Log.d("Preference = ", String.valueOf(second_checkbox));
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preference_activity, rootKey);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sensoractivity:
                openSensorActivity();
                return true;
            case R.id.settings:
                openSettingsMenu();
                return true;
            case R.id.numeOptiunea1:  // return True -> sunt aici
                return true;
            case R.id.numeOptiunea2:  // cos
                openCosMenu();
                return true;
            case R.id.numeOptiunea3:  // cont
                openContMenu();
                return true;
            case R.id.cameraactivity:
                openCameraMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openCameraMenu() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void openSensorActivity() {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

    public void openSettingsMenu() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openCosMenu() {
        Intent intent = new Intent(this, CosActivity.class);
        startActivity(intent);
    }

    public void openContMenu() {
        Intent intent = new Intent(this, ContActivity.class);
        startActivity(intent);
    }
}