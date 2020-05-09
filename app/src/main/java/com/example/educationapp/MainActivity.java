package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button playButton;
    private static final String NAME = "NAME";
    private Button settingsButton;
    TextView nameView;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameView = findViewById(R.id.textView);

        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener((v)-> {
            openGame();
        });

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener((v) -> {
            openSettings();
        });

    }
    public void openSettings(){
        Intent intent = new Intent(this,SettingsActivity.class);
    }
    public void openGame(){
        Intent intent = new Intent(this, GameActivity.class);
        name = nameView.getText().toString();
        intent.putExtra(NAME,name);
        startActivity(intent);
    }
}
