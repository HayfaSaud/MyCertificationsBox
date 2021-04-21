package com.example.cs410project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class certificatesMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates_menu);
        getSupportActionBar().hide();
        Button add = findViewById(R.id.addBtn);
        Button categories = findViewById(R.id.categories);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(certificatesMenu.this, addCertificate.class));
            }
        });
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(certificatesMenu.this, categories);
                menu.getMenuInflater().inflate(R.menu.categories, menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()){
                            case "Academic degree certificates":
                            startActivity(new Intent(certificatesMenu.this, AcademicCertificates.class));
                            break;
                            case "Courses certificates":
                            startActivity(new Intent(certificatesMenu.this, coursesCertificates.class));
                            break;
                            case "Volunteering certificates":
                            startActivity(new Intent(certificatesMenu.this, volunteeringCertificates.class));
                            break;
                        }
                        return true;
                    }
                });
                menu.show();
            }
        });
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
                        startActivity(new Intent(certificatesMenu.this, Logout.class));
                    }
                });
    }
}