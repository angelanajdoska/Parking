package com.example.parking;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationForm extends AppCompatActivity {

    DatePicker simpleDatePicker;
    TimePicker timePicker;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.reservation_form);
            Fragment fragment1 = getFragmentManager().findFragmentById(R.id.fragment1);
            Fragment fragment2 =  getFragmentManager().findFragmentById(R.id.fragment2);


        simpleDatePicker = (DatePicker) findViewById(R.id.datePicker);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
