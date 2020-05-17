package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.educationapp.game.Difficulty;

public class SettingsActivity extends AppCompatActivity {
Spinner diffSpinner;
private Difficulty difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        diffSpinner = findViewById(R.id.diffSpinner);
        String selection = diffSpinner.getSelectedItem().toString();
        difficulty = Difficulty.valueOf(selection.toUpperCase());
    }
}
