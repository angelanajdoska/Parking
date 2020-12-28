package com.example.parking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parking.models.Parking_places;

import java.util.ArrayList;
import java.util.List;

public class ParkingPlaces  extends AppCompatActivity {
    List<Parking_places> parkingPlaces;

    SQLiteDatabase sql;
    MyDatabase db;
    String city, username;
    String time, date;
    RecyclerView mRecyclerView;
    ParkingPlacesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_places);

        db = new MyDatabase(getApplicationContext());
        sql = db.getWritableDatabase();

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        city = intent.getStringExtra("city");
        username = intent.getStringExtra("username");

        parkingPlaces = getAllParkingPlaces();

        mRecyclerView = (RecyclerView) findViewById(R.id.parking_places);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ParkingPlacesAdapter(parkingPlaces, R.layout.parking_places_row, this, city, date, time, username, db);
        mRecyclerView.setAdapter(mAdapter);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.my_reservations_item) {
                Log.i("TAGG", "reservations clicked");
                Intent myReservationsIntent = new Intent(ParkingPlaces.this, MyReservationsActivity.class);
                myReservationsIntent.putExtra("username", username);
                startActivity(myReservationsIntent);
            }
            return true;
        });

    }
    private List<Parking_places> getAllParkingPlaces() {
        MyDatabase handler = new MyDatabase(ParkingPlaces.this);
        return handler.getAllParkingPlaces(city);
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
