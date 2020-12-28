package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText first_name;
    EditText last_name;
    EditText registration;
    EditText number;
    EditText username1;
    EditText password1;
    Button button;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               database_input(v);
            }
        });
    }
    public void database_input(View view){
        first_name=(EditText) findViewById(R.id.first_name);
        last_name=(EditText) findViewById(R.id.last_name);
        registration=(EditText) findViewById(R.id.registration);
        number=(EditText) findViewById(R.id.number);
        username1=(EditText) findViewById(R.id.username1);
        password1=(EditText) findViewById(R.id.pass);
        if (first_name.getText().toString().trim().length() == 0 || last_name.getText().toString().trim().length() == 0 ||
                registration.getText().toString().trim().length() == 0 || number.getText().toString().trim().length() == 0
                || username1.getText().toString().trim().length() == 0 || password1.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Сите полиња се задолжителни", Toast.LENGTH_SHORT).show();
        } else {
            MyDatabase myDatabase=new MyDatabase(RegisterActivity.this);
            myDatabase.insertUserDetails(first_name.getText().toString().trim(), last_name.getText().toString().trim(),
                    registration.getText().toString().trim(), number.getText().toString().trim(),
                    username1.getText().toString().trim(), password1.getText().toString().trim());
            Toast.makeText(this, "Успешно се регистриравте", Toast.LENGTH_SHORT).show();
            user=username1.getText().toString().trim();
            Intent intent = new Intent(this, Cities.class);
            intent.putExtra("username", user);
            startActivity(intent);
            //first_name.setText("");
        }
    }

}