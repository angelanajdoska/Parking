package com.example.parking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Confirmation extends AppCompatActivity {
    public static String parking, city, latitude, longitude, username, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        parking = intent.getStringExtra("parking");
        city = intent.getStringExtra("city");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.my_reservations_item) {
                Log.i("TAGG", "reservations clicked");
                Intent myReservationsIntent = new Intent(Confirmation.this, MyReservationsActivity.class);
                myReservationsIntent.putExtra("username", username);
                startActivity(myReservationsIntent);
            }
            return true;
        });
    }
    public static String getUsername1() {
        return username;
    }
    public static String getParkingName() {
        return parking;
    }
    public static String getCityName(){ return city;}
    public static String getMyDate(){ return date;}
    public static String getMyTime(){ return time;}
    public static String getLat() {
        return latitude;
    }
    public static String getLong() {
        return longitude;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.my_reservations_item) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
