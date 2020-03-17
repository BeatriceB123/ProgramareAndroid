package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView ProductText;
    private String[] ProductsList = new String[] {"Ice Fishing Game Frozen", "Rubber Keychain(Anna)","Anna Travel Doll","Anna and Kristoff Fashion Dolls","Elsa Jumbo Singing Plush","Eco Mug 400ml(Frozen)","Friend Olaf Feature Plush", "Sculpted Mug Olaf","Arendelle Castle Village Lego", "Elsas Jewellery Box Creation Lego", "Olaf Dress 3-4 years", "Walkie Talkie Frozen"};
    List<String> productsListArrayList = new ArrayList<>(Arrays.asList(ProductsList));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductText = findViewById(R.id.textView3);

        ArrayAdapter<String> itemListAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, productsListArrayList);

        ListView itemListView = findViewById(R.id.list_view);

        itemListView.setAdapter(itemListAdapter);

        itemListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ProductText.setText(ProductsList[i]);
                    }
                }
        );

        //incarca starea
        if(savedInstanceState != null){
            ProductText.setText(savedInstanceState.getCharSequence("randomText"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.numeOptiunea1:  // return True -> sunt aici
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

    @Override
    protected void onStart(){
        Log.d("mytag", "index = True");
        super.onStart();
    }

    @Override
    protected void onResume(){
        Log.d("mytag", "resume = True");
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.d("mytag", "pause = True");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.d("mytag", "stop = True");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.d("mytag", "destroy = True");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putCharSequence("randomText", ProductText.getText());
    }
}
