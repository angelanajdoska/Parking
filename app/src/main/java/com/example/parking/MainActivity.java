package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText username;
    EditText password;
    EditText first_name;
    EditText last_name;
    EditText address;
    EditText number;
    EditText username1;
    EditText password1;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_check(v);
            }
        });
        db = openOrCreateDatabase("Users", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS registered_users(firstname VARCHAR, lastname VARCHAR, " +
                "address VARCHAR, number VARCHAR, username VARCHAR, password VARCHAR);");
    }
    public void database_input(View view){
        first_name=(EditText) findViewById(R.id.first_name);
        last_name=(EditText) findViewById(R.id.last_name);
        address=(EditText) findViewById(R.id.address);
        number=(EditText) findViewById(R.id.number);
        username1=(EditText) findViewById(R.id.username1);
        password1=(EditText) findViewById(R.id.pass);
        if (first_name.getText().toString().trim().length() == 0 || last_name.getText().toString().trim().length() == 0 ||
                address.getText().toString().trim().length() == 0 || number.getText().toString().trim().length() == 0
        || username1.getText().toString().trim().length() == 0 || password1.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Сите полиња се задолжителни", Toast.LENGTH_SHORT).show();
        } else {
            db.execSQL("INSERT INTO registered_users (firstname, lastname, address, number, username, password) " +
                    "VALUES('" + first_name.getText().toString() + "', '" + last_name.getText().toString() + "', " +
                    "'" + address.getText().toString() + "', '" + number.getText().toString() + "', " +
                    "'" + username1.getText().toString() + "', '" + password1.getText().toString() + "');");
            Toast.makeText(this, "Успешно се регистриравте", Toast.LENGTH_SHORT).show();
            //first_name.setText("");
        }
    }

    public void database_check(View view) {
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        if(username.getText().toString().trim().length() == 0 || password.getText().toString().trim().length() == 0){
            Toast.makeText(this, "Двете полиња се задолжителни", Toast.LENGTH_LONG).show();
        }
        else{
            Cursor c1 = db.rawQuery("SELECT * FROM registered_users WHERE username = '" + username.getText().toString() + "'", null);
            Cursor c2 = db.rawQuery("SELECT * FROM registered_users WHERE password = '" + password.getText().toString() + "'", null);
            if(c1.moveToFirst() && c2.moveToFirst()) {
                Intent intent = new Intent(this, Cities.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Корисничкото име или лозинката е погрешна или не сте регистрирани", Toast.LENGTH_LONG).show();
            }
            c1.close();
            c2.close();
        }
    }
}