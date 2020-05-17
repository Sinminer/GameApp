package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.educationapp.game.MenuFragment;

public class MainActivity extends AppCompatActivity {
    private Button playButton;
    private static final String NAME = "NAME";
    private Button settingsButton;
    EditText nameView;
    String name;
    ImageButton helpButton;
    private AlertDialog helpAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameView = findViewById(R.id.textView);
        name = nameView.getText().toString();
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener((v) -> {
            openGame();
        });

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener((v) -> {
            openSettings();
        });

    }
    public void openSettings(){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
    public void openGame(){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(NAME,name);
        startActivity(intent);
    }
}
