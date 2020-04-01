package com.example.lab1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@SuppressWarnings("deprecation")

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    TextView imagePath;

    Camera.PictureCallback jpegCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        int MY_PERMISSION_CAMERA = 1;
        if (ContextCompat.checkSelfPermission( this,android.Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String [] { android.Manifest.permission.CAMERA },
                    MY_PERMISSION_CAMERA
            );
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        imagePath = findViewById(R.id.textViewPathPoza);
        surfaceHolder.addCallback(this);

        jpegCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                try {
                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    File mypath = new File(directory,System.currentTimeMillis() + "");
                    outStream = new FileOutputStream(mypath);
                    outStream.write(data);
                    imagePath.setText(mypath.getAbsolutePath());
                    Toast.makeText(getApplicationContext(), "Poza salvata", Toast.LENGTH_LONG).show();
                    outStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                refreshCamera();
            }
        };
    }

    public void captureImage(View v) {
        camera.takePicture(null, null, jpegCallback);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            camera = Camera.open();
        } catch (Exception e) {
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();

        List<Camera.Size> sizes = param.getSupportedPreviewSizes();
        Camera.Size selected = sizes.get(0);
        param.setPreviewSize(selected.width, selected.height);
        camera.setParameters(param);
        try {
            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        refreshCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        try {
            camera.stopPreview();
        } catch (Exception ignored) { }

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception ignored) { }
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}