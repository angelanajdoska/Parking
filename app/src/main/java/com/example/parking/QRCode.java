package com.example.parking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRCode extends AppCompatActivity {
    String username, date, time, parking, city;
    ImageView imageView;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;
    String TAG = "GenerateQRCode";
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        username = Confirmation.getUsername1();
        city = Confirmation.getCityName();
        parking = Confirmation.getParkingName();
        date = Confirmation.getMyDate();
        time = Confirmation.getMyTime();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.my_reservations_item) {
                Log.i("TAGG", "reservations clicked");
                Intent myReservationsIntent = new Intent(QRCode.this, MyReservationsActivity.class);
                myReservationsIntent.putExtra("username", username);
                startActivity(myReservationsIntent);
            }
            return true;
        });

        String inputValue;
        inputValue = username + " | " + date + " | " + time + " | " + parking + " | " + city;
        qrImage = (ImageView) findViewById(R.id.QR_Image);

        if (inputValue.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    inputValue, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                qrImage.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }

        }

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
