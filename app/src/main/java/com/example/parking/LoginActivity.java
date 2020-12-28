package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button button;
    Button button1;
    EditText username;
    EditText password;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_check(v);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
    public void database_check(View view) {
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        if(username.getText().toString().trim().length() == 0 || password.getText().toString().trim().length() == 0){
            Toast.makeText(this, "Двете полиња се задолжителни", Toast.LENGTH_LONG).show();
        }
        else{
            MyDatabase myDatabase=new MyDatabase(LoginActivity.this);
            if(myDatabase.checkUser(username.getText().toString().trim()
                    , password.getText().toString().trim())) {
                user = username.getText().toString().trim();
                Intent intent = new Intent(this, Cities.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Корисничкото име или лозинката е погрешна или не сте регистрирани", Toast.LENGTH_LONG).show();
            }
        }
    }
}