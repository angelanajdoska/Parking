package com.example.parking;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ConfirmationFragment1 extends Fragment {
    private static final int REQUEST_CODE_DETAILS_ACTIVITY = 1234;
    String username, date, time, parking, city;
    public ConfirmationFragment1() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment1, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        username = Confirmation.getUsername1();
        Button button1 = (Button) getActivity().findViewById(R.id.qr);
        Button button = (Button) getActivity().findViewById(R.id.navigate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCityLocation();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QRCode.class);
                startActivity(intent);
            }
        });

    }
    private void showCityLocation() {
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            // show in same activity
            ConfirmationFragment2 frag = (ConfirmationFragment2) getFragmentManager().findFragmentById(R.id.fragment2);

        } else {
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            startActivityForResult(intent, REQUEST_CODE_DETAILS_ACTIVITY);
        }
    }
}
