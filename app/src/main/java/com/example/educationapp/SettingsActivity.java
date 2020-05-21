package com.example.educationapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.educationapp.game.Difficulty;

public class SettingsActivity extends AppCompatActivity {
    Spinner diffSpinner;
    public static Difficulty level = Difficulty.EASY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        diffSpinner = findViewById(R.id.diffSpinner);
        diffSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLevel();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setLevel();
    }

    public void setLevel() {
        String selection = diffSpinner.getSelectedItem().toString();
        level = Difficulty.valueOf(selection.toUpperCase());
    }


}
