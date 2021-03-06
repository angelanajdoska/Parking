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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class Cities extends AppCompatActivity {

    public static String username;
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    int image_id[] ={R.drawable.krushevo, R.drawable.ohrid, R.drawable.skopje};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.my_reservations_item) {
                Log.i("TAGG", "reservations clicked");
                Intent myReservationsIntent = new Intent(Cities.this, MyReservationsActivity.class);
                myReservationsIntent.putExtra("username", username);
                startActivity(myReservationsIntent);
            }
            return true;
        });

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        List<String> values = Arrays.asList("Крушево", "Охрид", "Скопје" );
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyAdapter(values, R.layout.my_row, this, image_id, username);
        mRecyclerView.setAdapter(mAdapter);

    }
    public static String getUsername() {
        return username;
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
