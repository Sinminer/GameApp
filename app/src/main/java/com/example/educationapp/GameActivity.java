package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.educationapp.game.HangmanFragment;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fragmentManager;
    HangmanFragment hangmanFragment;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,new HangmanFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {

    }

    public void letterPressed(View view) {
        hangmanFragment.letterPressed(view);
    }
}
