package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;


public class SensorActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mGravity;
    private Sensor mTemperature;
    private Sensor mLight;
    private Sensor mHumidity;
    private Sensor mAccelerometer;
    private Sensor mGyroscope; // uncalibrated

    private TextView Longitude;
    private TextView Latitude;

    private TextView xGravity;
    private TextView yGravity;
    private TextView zGravity;

    private TextView temperature;
    private TextView light;
    private TextView humidity;

    private TextView xAccelerometer;
    private TextView yAccelerometer;
    private TextView zAccelerometer;

    private TextView xGyroscopeU;
    private TextView yGyroscopeU;
    private TextView zGyroscopeU;
    private TextView xGyroscopeE;
    private TextView yGyroscopeE;
    private TextView zGyroscopeE;


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Longitude.setText(String.valueOf(location.getLongitude()));
            Latitude.setText(String.valueOf(location.getLatitude()));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        Longitude = findViewById(R.id.longitude);
        Latitude = findViewById(R.id.latitude);
        xGravity = findViewById(R.id.xValueGravity);
        yGravity = findViewById(R.id.yValueGravity);
        zGravity = findViewById(R.id.zValueGravity);
        temperature = findViewById(R.id.temperatureValue);
        light = findViewById(R.id.lightValue);
        humidity = findViewById(R.id.umiditateValoare);

        xAccelerometer = findViewById(R.id.xAccelometruValoare);
        yAccelerometer = findViewById(R.id.yAccelometruValoare);
        zAccelerometer = findViewById(R.id.zAccelometruValoare);

        xGyroscopeU = findViewById(R.id.xGiroscopUncalib);
        yGyroscopeU = findViewById(R.id.yGiroscopUncalib);
        zGyroscopeU = findViewById(R.id.zGiroscopUncalib);

        xGyroscopeE = findViewById(R.id.xGiroscopEstimated);
        yGyroscopeE = findViewById(R.id.yGiroscopEstimated);
        zGyroscopeE = findViewById(R.id.zGiroscopEstimated);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mTemperature = Objects.requireNonNull(mSensorManager).getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);


        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);

        android.location.LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;

        if (ContextCompat.checkSelfPermission( this,android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String [] { android.Manifest.permission.ACCESS_FINE_LOCATION },
                    MY_PERMISSION_ACCESS_FINE_LOCATION
            );
        }

        Objects.requireNonNull(locationManager).requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, mLocationListener);

        //ListView lv = findViewById(R.id.textSensorActivity);
        //List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        //lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deviceSensors));
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);

    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            temperature.setText(String.valueOf(event.values[0]));
        }
        else if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            light.setText(String.valueOf(event.values[0]));
            //  Ambient light level in SI lux units
        }
        else if(event.sensor.getType() == Sensor.TYPE_GRAVITY){
            xGravity.setText(String.valueOf(event.values[0]));
            yGravity.setText(String.valueOf(event.values[1]));
            zGravity.setText(String.valueOf(event.values[2]));
            // A three dimensional vector indicating the direction and magnitude of gravity. Units are m/s^2.
        }
        else if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE_UNCALIBRATED){
            xGyroscopeU.setText(String.valueOf(event.values[0]));
            yGyroscopeU.setText(String.valueOf(event.values[1]));
            zGyroscopeU.setText(String.valueOf(event.values[2]));
            xGyroscopeE.setText(String.valueOf(event.values[3]));
            yGyroscopeE.setText(String.valueOf(event.values[4]));
            zGyroscopeE.setText(String.valueOf(event.values[5]));
        }
        else if(event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            humidity.setText(String.valueOf(event.values[0]));
            // Relative ambient air humidity in percent
        }
        else if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            xAccelerometer.setText(String.valueOf(event.values[0]));
            yAccelerometer.setText(String.valueOf(event.values[1]));
            zAccelerometer.setText(String.valueOf(event.values[2]));
            // Relative ambient air humidity in percent
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                openSettingsMenu();
                return true;
            case R.id.numeOptiunea1:  //main
                return true;
            case R.id.numeOptiunea2:  // cos
                openCosMenu();
                return true;
            case R.id.numeOptiunea3:  // cont
                openContMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
