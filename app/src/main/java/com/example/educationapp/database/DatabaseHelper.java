package com.example.educationapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Education";
    private static final int DB_VERSION = 0;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PLAYERSCORE (_id INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME  TEXT," + " INTERGER SCORE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDataBase(db, oldVersion, newVersion);
    }

    public void updateDataBase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE PLAYERSCORE (_id INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME  TEXT," + " INTERGER SCORE)");
            insertScore("PLAYERSCORE", "Rob", 20);
            insertScore("PLAYERSCORE", "Tom", 9);
        }
        if (newVersion < 2) {
        }
    }

    public void insertScore(String TABLE, String name, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("SCORE", score);
        db.insert(TABLE, null, values);
    }
}
