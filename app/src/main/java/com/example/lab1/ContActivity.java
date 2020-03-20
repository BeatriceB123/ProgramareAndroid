package com.example.lab1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ContActivity extends AppCompatActivity {

    protected TextView SaveCodPostal;
    protected TextView SaveNumarTelefon;
    protected TextView SaveAdresa;
    protected TextView LoadCodPostal;
    protected TextView LoadNumarTelefon;
    protected TextView LoadAdresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont);
        SaveCodPostal = findViewById(R.id.to_save3);
        SaveAdresa = findViewById(R.id.to_save2);
        SaveNumarTelefon = findViewById(R.id.to_save1);

        LoadCodPostal = findViewById(R.id.to_save3);
        LoadAdresa = findViewById(R.id.to_save2);
        LoadNumarTelefon = findViewById(R.id.to_save1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                openSettingsMenu();
                return true;
            case R.id.numeOptiunea1:
                openCatalogMenu();
                return true;
            case R.id.numeOptiunea2:  // cos
                openCosMenu();
                return true;
            case R.id.numeOptiunea3:  // cont -> sunt aici
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openCosMenu() {
        Intent intent = new Intent(this, CosActivity.class);
        startActivity(intent);
    }

    public void openSettingsMenu() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openCatalogMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void alertDialogTest(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(ContActivity.this).create();
        alertDialog.setTitle("Atentie");
        alertDialog.setMessage("Dialog deschis cu succes :)");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void saveText(View view) {
        Context context = this;
        String saveText1 = SaveNumarTelefon.getText().toString();
        String saveText2 = SaveAdresa.getText().toString();
        String saveText3 = SaveCodPostal.getText().toString();
        try (FileOutputStream fos = context.openFileOutput("file_with_text", Context.MODE_PRIVATE)) {
            fos.write(saveText1.getBytes());
            fos.write('\n');
            fos.write(saveText2.getBytes());
            fos.write('\n');
            fos.write(saveText3.getBytes());
        }
        catch (Exception ignored) { }
    }

    public void loadText(View view)
    {
        Context context = this;
        try(FileInputStream fis = context.openFileInput("file_with_text")) {
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String[] texts = new String[5];
                texts[0] = reader.readLine();
                int i = 0;
                while (texts[i] != null) {
                    texts[++i] = reader.readLine();
                }
                LoadNumarTelefon.setText(texts[0]);
                LoadAdresa.setText(texts[1]);
                LoadCodPostal.setText(texts[2]);
            }
            catch (Exception ignored) { }
        }
        catch(Exception ignored) { }
    }

}
