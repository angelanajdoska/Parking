package com.example.parking;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.parking.models.Parking_places;

import java.util.ArrayList;
import java.util.List;

public class ReservationForm extends AppCompatActivity {

    String city, username;
    String time;
    DatePicker simpleDatePicker;
    Button button;
    String selectedDate;
    int day, month, year;
    List<Parking_places> parkingList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_form);
        Fragment fragment1 = getFragmentManager().findFragmentById(R.id.fragment1);
        Fragment fragment2 = getFragmentManager().findFragmentById(R.id.fragment2);
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        username = intent.getStringExtra("username");

        simpleDatePicker = (DatePicker) findViewById(R.id.datePicker);
        simpleDatePicker.setMinDate(System.currentTimeMillis() - 1000);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        day = simpleDatePicker.getDayOfMonth();
        month = simpleDatePicker.getMonth() + 1;
        year = simpleDatePicker.getYear();
        selectedDate = day + "/" + month + "/" + year;
        time = spinner.getSelectedItem().toString();

        button = (Button) findViewById(R.id.reserve);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ParkingPlaces.class);
                intent.putExtra("date", selectedDate);
                intent.putExtra("time", time);
                intent.putExtra("city", city);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.my_reservations_item) {
                Log.i("TAGG", "reservations clicked");
                Intent myReservationsIntent = new Intent(ReservationForm.this, MyReservationsActivity.class);
                myReservationsIntent.putExtra("username", username);
                startActivity(myReservationsIntent);
            }
            return true;
        });
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

    // private List<Parking_places> getAllParkingPlaces() {
      //  MyDatabase handler = new MyDatabase(ReservationForm.this);
        //return handler.getAllParkingPlaces(city);
    //}
    //private void ListParkingPlaces() {

     //   List<Parking_places> ParkingPlaces = getAllParkingPlaces();

       // AppCompatActivity activity = ReservationForm.this;
       // ParkingPlaces parking =
         //       new ParkingPlaces(ParkingPlaces);
   // }
}
