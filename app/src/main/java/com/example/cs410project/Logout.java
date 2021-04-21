package com.example.cs410project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Logout extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(Logout.this, LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}