package com.example.educationapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Assigning global variables for the DatabaseHelper class, using DBVersion 1 because there was no
//reason to upgrade.
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Education";
    private static final int DB_VERSION = 1;

    //Passing context in for usage in activities, passing Database name and Version.
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //OnCreate calls updateDatabase passing in the database and the version numbers.
    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDataBase(db, 0, DB_VERSION);
    }

    //OnUpgrade calls updateDatabase passing in the database and the version numbers.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDataBase(db, oldVersion, newVersion);
    }

    //UpdateDatabase checks version numbers and if the database is newer then creates the database and
    //inserts static scores for testing.
    private void updateDataBase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE PLAYERSCORE (_id INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME TEXT," + " SCORE INTERGER );");
            insertScore(db, "PLAYERSCORE", "Rob", 20);
            insertScore(db, "PLAYERSCORE", "Tom", 9);
        }
        if (oldVersion < newVersion) {
            System.out.println("Database Updated.");
        }
    }

    //Insert Score is a method that takes values from the game activity and places them into the database
    //it checks if the database is available and if it doesn't exist it allows it to be written too.
    public void insertScore(SQLiteDatabase db, String TABLE, String name, int score) {
        if (db == null) {
            db = this.getWritableDatabase();
        }
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("SCORE", score);
        db.insert(TABLE, null, values);
    }
}
