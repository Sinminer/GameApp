package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.example.educationapp.game.HangmanFragment;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fragmentManager;
    HangmanFragment hangmanFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

    }

    @Override
    public void onClick(View v) {

    }

    public void letterPressed(View view) {
        hangmanFragment.letterPressed(view);
    }
}
