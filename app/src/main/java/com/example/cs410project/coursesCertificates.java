package com.example.cs410project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class coursesCertificates extends AppCompatActivity {
    dbHelper helper = new dbHelper(this);
    SQLiteDatabase db;
    ArrayList<String> names, ids, orgs, dates;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_certificates);
        getSupportActionBar().hide();
        list = findViewById(R.id.courseslist);
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
                startActivity(new Intent(coursesCertificates.this, Logout.class));
            }
        });
    }

    @Override
    protected void onResume() {
        displayData();
        super.onResume();
    }
    public void displayData(){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, id, org, releaseDate FROM certificate WHERE category='Courses certificate'", null);
//        ids.clear();
//        names.clear();
//        orgs.clear();
//        dates.clear();
        if(cursor.moveToFirst()){
            do{
                names.add(cursor.getString(cursor.getColumnIndex("name")));
                ids.add(cursor.getString(cursor.getColumnIndex("id")));
                orgs.add(cursor.getString(cursor.getColumnIndex("org")));
                dates.add(cursor.getString(cursor.getColumnIndex("releaseDate")));
            } while (cursor.moveToNext());
        }
        customAdapter adapter = new customAdapter(coursesCertificates.this, names,ids, orgs, dates);
        list.setAdapter(adapter);
        cursor.close();
    }
}