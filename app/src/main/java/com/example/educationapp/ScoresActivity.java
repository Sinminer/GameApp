package com.example.educationapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.educationapp.database.DatabaseHelper;

public class ScoresActivity extends AppCompatActivity {
    ListView scoreNames;
    ListView scoreScores;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        scoreNames = findViewById(R.id.scoreNames);
        scoreScores = findViewById(R.id.scoreScores);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        //Cursor accesses the database to display the names and scores in the HighScoresActivity.
        try {
            SQLiteDatabase database = databaseHelper.getReadableDatabase();
            cursor = database.query("PLAYERSCORE", new String[]{"_id", "NAME", "SCORE"},
                    null, null, null, null, null);
            SimpleCursorAdapter nameAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"},
                    new int[]{android.R.id.text1}, 0);
            SimpleCursorAdapter scoreAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1, cursor, new String[]{"SCORE"},
                    new int[]{android.R.id.text1}, 0);
            scoreScores.setAdapter(scoreAdapter);
            scoreNames.setAdapter(nameAdapter);

        } catch (SQLiteException e) {
            System.out.println(e.toString());
        }


    }

}
