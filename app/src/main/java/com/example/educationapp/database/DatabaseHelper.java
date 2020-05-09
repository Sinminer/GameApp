package com.example.educationapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Education";
    private static final int DB_VERSION = 0;

    DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE PLAYERSCORE (_id INTEGER PRIMARY KEY AUTOINCREMENT," +  "NAME  TEXT,"+" INTERGER SCORE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
