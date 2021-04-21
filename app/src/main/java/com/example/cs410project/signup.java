package com.example.cs410project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    EditText name, password, confirmEditTxt;
    String username, pass, confirm;
    dbHelper db = new dbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        name = findViewById(R.id.usernameSignup);
        username = name.getText().toString();
        password = findViewById(R.id.passSignup);
        pass = password.getText().toString();
        confirmEditTxt = findViewById(R.id.confirm);
        confirm = confirmEditTxt.getText().toString();
        Button signupBtn = findViewById(R.id.signup);
        Button back = findViewById(R.id.backToLogin);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.equals(confirm)){
                boolean isExists = db.checkUserExistence(username, pass);
                if (!isExists) {
                    boolean insert = db.insertUser(username, pass);
                    if (insert) {
                        Toast.makeText(getApplicationContext(), "certificate added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                } else if (isExists) {
                    Toast.makeText(getApplicationContext(), "already exist username", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
                else if(!(pass.equals(confirm))){
                    Toast.makeText(getApplicationContext(), "password fields are not identical, please re-enter your password",
                            Toast.LENGTH_SHORT).show();
                }
}});
        }
}