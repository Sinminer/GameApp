package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.educationapp.game.HangmanFragment;
import com.example.educationapp.game.LightFragment;
import com.example.educationapp.game.MenuFragment;
import com.example.educationapp.game.ShakerFragment;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener, View.OnClickListener, SensorEventListener {

    HangmanFragment hangmanFragment;
    LightFragment lightFragment;
    TextView updateScore;
    ShakerFragment shakerFragment;
    ArrayList<Fragment> fragments;
    public static int SCORE = 0;
    ImageButton helpButton;
    Random random;
     public static GameActivity gameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameActivity = this;
        helpButton = findViewById(R.id.help);
        random = new Random();
        hangmanFragment = new HangmanFragment();
        shakerFragment = new ShakerFragment();
        lightFragment = new LightFragment();
        fragments = new ArrayList<>(4);
        fragments.add(hangmanFragment);
        fragments.add(shakerFragment);
        fragments.add(lightFragment);
        setContentView(R.layout.activity_game);
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

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void endGame(){
        SCORE++;
        updateScore = findViewById(R.id.score);
       updateScore.setText(String.format("Score: %d", SCORE));
        FragmentTransaction fragmentTransaction;
         fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragments.get(random.nextInt(3)));
        fragmentTransaction.commit();
    }
    @SuppressLint("DefaultLocale")
    public void lostGame(){
        updateScore = findViewById(R.id.score);
        updateScore.setText(String.format("Score: %d", SCORE));
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
}
