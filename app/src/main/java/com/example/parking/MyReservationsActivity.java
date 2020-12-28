package com.example.parking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.parking.models.Parking_places;
import com.example.parking.models.Reservation;

import java.util.List;

public class MyReservationsActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyReservationsAdapter mAdapter;
    String username;
    String user;
    List<Reservation> reservationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        reservationList = getAllReservations();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_reservations);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyReservationsAdapter(reservationList, MyReservationsActivity.this );
        mRecyclerView.setAdapter(mAdapter);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        user=Cities.getUsername();

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
    private List<Reservation> getAllReservations() {
        MyDatabase handler = new MyDatabase(MyReservationsActivity.this);
        return handler.getAllReservations();
    }
}