package com.example.andrew.ivegan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends AppCompatActivity {
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.loginBut);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, CardListActivity.class);
                myIntent.putExtra("key", 1); //Optional parameters
                LoginActivity.this.startActivity(myIntent);
            }
        });

        registerButton = findViewById(R.id.registerBut);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                myIntent.putExtra("key", 1); //Optional parameters
                LoginActivity.this.startActivity(myIntent);
            }
        });
    }
}
