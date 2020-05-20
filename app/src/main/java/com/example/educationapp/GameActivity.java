package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;


import android.annotation.SuppressLint;
import android.content.Intent;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;

import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.educationapp.database.DatabaseHelper;
import com.example.educationapp.game.HangmanFragment;
import com.example.educationapp.game.LightFragment;


import com.example.educationapp.game.ShakerFragment;


import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener, View.OnClickListener, SensorEventListener {
    HangmanFragment hangmanFragment;
    DatabaseHelper databaseHelper;
    LightFragment lightFragment;
    TextView updateScore;
    ShakerFragment shakerFragment;
    ArrayList<Fragment> fragments;
    private static final String NAME = "NAME";
    public static int score = 0;
    String name;
    Random random;
    FrameLayout frameLayout;
    Intent mainIntent;
     @SuppressLint("StaticFieldLeak")
     public static GameActivity gameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mainIntent = getIntent();
        name = mainIntent.getStringExtra(NAME);
        frameLayout = findViewById(R.id.fragment_container);
        gameActivity = this;
        random = new Random();
        hangmanFragment = new HangmanFragment();
        shakerFragment = new ShakerFragment();
        lightFragment = new LightFragment();
        fragments = new ArrayList<>(4);


        fragments.add(hangmanFragment);
        fragments.add(shakerFragment);
        fragments.add(lightFragment);

         databaseHelper = new DatabaseHelper(this);

    }
    public void startGame(){
        frameLayout.setVisibility(View.VISIBLE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragments.get(random.nextInt(3)));
        fragmentTransaction.commit();
    }

    public void letterPressed(View view) {
        hangmanFragment.letterPressed(view);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        lightFragment.onClick(v);
    }

    public void endGame(){
        score++;
        updateScore = findViewById(R.id.score);
       updateScore.setText(String.format(Locale.getDefault(),"Score: %d", score));
        FragmentTransaction fragmentTransaction;
         fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragments.get(random.nextInt(3)));
        fragmentTransaction.commit();
    }

    public void lostGame(){
        updateScore = findViewById(R.id.score);
        updateScore.setText(String.format(Locale.getDefault(),"Score: %d", score));
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragments.get(random.nextInt(3)));
        fragmentTransaction.commit();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        shakerFragment.onSensorChanged(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        shakerFragment.onAccuracyChanged(sensor,accuracy);
    }
    public void updateScoreBoard(){
        databaseHelper.insertScore(databaseHelper.getDatabase(),"PLAYERSCORE",name, score);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
