package com.example.parking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parking.models.Parking_places;
import com.example.parking.models.Reservation;

import java.util.List;

public class MyReservationsAdapter  extends RecyclerView.Adapter<MyReservationsAdapter.ViewHolder>{
    List<Reservation> reservationList;
    Context context;

    @NonNull
    @Override
    public MyReservationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_reservations_row, parent, false);
        return new MyReservationsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReservationsAdapter.ViewHolder holder, int position) {
       Reservation entry = reservationList.get(position);
        holder.cityName.setText(entry.getCityName());
        holder.parkingName.setText(entry.getParkingName());
        holder.date.setText(entry.getDate());
        holder.time.setText(entry.getTime());
    }

    @Override
    public int getItemCount() {
        return reservationList == null ? 0 : reservationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cityName, parkingName, date, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.city);
            parkingName = (TextView) itemView.findViewById(R.id.parking_name);
            date = (TextView) itemView.findViewById(R.id.my_date);
            time = (TextView) itemView.findViewById(R.id.my_time);

        }
    }
    public MyReservationsAdapter(List<Reservation> myList, Context context){
        this.reservationList = myList;
        this.context = context;
    }
}
