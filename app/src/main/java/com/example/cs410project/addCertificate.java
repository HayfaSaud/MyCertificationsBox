package com.example.cs410project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class addCertificate extends AppCompatActivity {
    dbHelper helper = new dbHelper(this);
    SQLiteDatabase database;
    EditText nameEdittxt, idEdittxt, orgEdittxt, releaseDateEdittxt;
    String  name,id,org, releaseDate;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);
        getSupportActionBar().hide();
        nameEdittxt = findViewById(R.id.studentName);
        idEdittxt = findViewById(R.id.studentID);
        orgEdittxt = findViewById(R.id.org);
        releaseDateEdittxt = findViewById(R.id.releaseDate);
        name = nameEdittxt.getText().toString();
        id = idEdittxt.getText().toString();
        org = orgEdittxt.getText().toString();
        releaseDate = releaseDateEdittxt.getText().toString();
        Spinner typeSpinner = findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        String category = typeSpinner.getSelectedItem().toString();
        add = findViewById(R.id.Add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    boolean insert = helper.insertCertificate(id, name, org, releaseDate, category);
                    if (insert){
                        AlertDialog alertDialog = new AlertDialog.Builder(addCertificate.this).create();
                        alertDialog.setTitle("Certificates added successfully");
                        alertDialog.setContentView(R.layout.alert);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                    else
                        Toast.makeText(addCertificate.this, "Error", Toast.LENGTH_SHORT).show();
        }});
        ImageView back = findViewById(R.id.back);
        TextView logout = findViewById(R.id.logoutBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addCertificate.this, Logout.class));
            }
        });

    }
}