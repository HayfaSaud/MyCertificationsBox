package com.example.cs410project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "project.db";
    Context context;
    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user (username TEXT PRIMARY KEY, pass TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS certificate (name text , id TEXT  PRIMARY KEY , org TEXT, releaseDate TEXT, category TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS  certificate");
        onCreate(db);
    }
//    public boolean isExists(String username, String pass){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=? and pass=?",  new String[] {username, pass});
//        if(cursor.getCount()>0)
//            return true;
//        else
//            return false;
//    }
    //to add certificate
    public boolean insertCertificate(String name, String id, String org, String releaseDate, String category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("id", id);
        cv.put("org", org);
        cv.put("releaseDate", releaseDate);
        cv.put("category", category);
        long result = db.insert("certificate",null, cv);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean insertUser(String username, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", username);
        cv.put("pass", pass);

        long result = db.insert("user",null, cv);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

//    public boolean deleteInfo(String id){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("DELETE FROM certificate WHERE id=?", new String[]{id});
//        if(cursor.getCount()>0){
//            return true;
//        }else {
//            return false;
//        }
//    }
    public boolean Delete (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete("certificate","id=?",new String[]{id});
        if(result> 0)
            return true;
        else
            return false;
}
    public boolean checkUserExistence(String username, String pass){
        String[] col = {"username"};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "username=? and pass=?";
        String[] selectionArgs = {username, pass};
        Cursor cursor = db.query("user",col,selection,selectionArgs,null,null, null);
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

}
