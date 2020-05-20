package com.example.educationapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Education";
    private static final int DB_VERSION = 1;
    public static SQLiteDatabase database;
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        updateDataBase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDataBase(db, oldVersion, newVersion);
    }

    public void updateDataBase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE PLAYERSCORE (_id INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME TEXT," + " SCORE INTERGER );");
            insertScore(db,"PLAYERSCORE", "Rob", 20);
            insertScore(db,"PLAYERSCORE", "Tom", 9);
        }
    }

    public void insertScore(SQLiteDatabase db,String TABLE, String name, int score) {
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("SCORE", score);
        db.insert(TABLE, null, values);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}
