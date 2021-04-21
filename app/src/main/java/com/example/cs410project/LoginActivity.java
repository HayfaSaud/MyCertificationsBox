package com.example.cs410project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    dbHelper helper = new dbHelper(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         getSupportActionBar().hide();
         EditText usernameEditText = findViewById(R.id.username);
         EditText passwordEditText = findViewById(R.id.pass);
         Button loginButton = findViewById(R.id.login);
         Button register = findViewById(R.id.register);
         register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(LoginActivity.this, signup.class));
             }
         });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String username = usernameEditText.toString();
                    String pass = passwordEditText.toString();
                    boolean isExists = helper.checkUserExistence(username, pass);
                    if (isExists) {
                        Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, certificatesMenu.class));
                    }
                    else{
                        passwordEditText.setText(null);
                        Toast.makeText(LoginActivity.this, "Error, please check username", Toast.LENGTH_SHORT).show();
                }}
            }
        );
        }}