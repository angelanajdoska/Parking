package com.example.parking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parking.models.Parking_places;
import com.example.parking.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ParkingPlacesAdapter extends RecyclerView.Adapter<ParkingPlacesAdapter.ViewHolder> {
    private MyDatabase mDatabase;
    List<Parking_places> myList;
    private int rowLayout;
    private Context mContext;
    String city;
    String date, time, username;
    Integer free, taken;
    Integer resPerUser;

    @NonNull
    @Override
    public ParkingPlacesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_places_row, parent, false);
        return new ParkingPlacesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingPlacesAdapter.ViewHolder viewHolder, int i) {
        Parking_places entry = myList.get(i);
        viewHolder.myName.setText(entry.getParkingName());
        free=mDatabase.getTotalSpaces(entry.getParkingName()) - mDatabase.getNumberOfReservations(date, time, entry.getParkingName());
        taken=entry.getTotal()-free;
        String t = String.valueOf(taken);
        String f = String.valueOf(free);
        viewHolder.first.setText(f);
        viewHolder.second.setText(t);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resPerUser=mDatabase.numberResPerUser(username);
                if (resPerUser > 3){
                    Toast.makeText(mContext, "Не смеете да имате повеќе од три резервации", Toast.LENGTH_LONG).show();
                }
                else {
                Intent intent = new Intent(mContext, Confirmation.class);
                    intent.putExtra("username", username);
                    intent.putExtra("parking", entry.getParkingName());
                    intent.putExtra("city", entry.getCityName());
                    intent.putExtra("date", date);
                    intent.putExtra("time", time);
                    intent.putExtra("latitude", entry.getLatitude());
                    intent.putExtra("longitude", entry.getLongitude());
                    mContext.startActivity(intent);
                    mDatabase.insertReservationDetails(username, city, date, time, entry.getParkingName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myName;
        public Button button;
        public TextView first;
        public TextView second;


        public ViewHolder(View itemView) {
            super(itemView);
            myName = (TextView) itemView.findViewById(R.id.parking);
            button=(Button) itemView.findViewById(R.id.b);
            first=(TextView) itemView.findViewById(R.id.second);
            second=(TextView) itemView.findViewById(R.id.first);
        }
    }
    public ParkingPlacesAdapter(List<Parking_places> myList, int rowLayout, Context context, String city, String date, String time, String username, MyDatabase mDatabase) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.city=city;
        this.date=date;
        this.time=time;
        this.username=username;
        this.mDatabase=mDatabase;
    }
}
