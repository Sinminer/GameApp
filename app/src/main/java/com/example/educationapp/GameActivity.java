package com.example.educationapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.educationapp.database.DatabaseHelper;
import com.example.educationapp.game.HangmanFragment;
import com.example.educationapp.game.LightFragment;
import com.example.educationapp.game.ShakerFragment;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class GameActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, SensorEventListener {
    HangmanFragment hangmanFragment;
    LightFragment lightFragment;
    ShakerFragment shakerFragment;

    DatabaseHelper databaseHelper;
    FrameLayout frameLayout;
    TextView updateScore;

    private static final String NAME = "NAME";
    String name;
    public static int score = 0;

    ArrayList<Fragment> fragments;

    Random random;

    Intent mainIntent;
    @SuppressLint("StaticFieldLeak")
    public static GameActivity gameActivity;

    @Override
    //OnCreate determines if the view is a large layout or a small layout to size the activity.
    //adds all the game fragments into a list for usage.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_game);
        } else {
            setContentView(R.layout.activity_game_large);
        }

        gameActivity = this;

        mainIntent = getIntent();
        name = mainIntent.getStringExtra(NAME);

        frameLayout = findViewById(R.id.fragment_container);

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

    public void startGame() {
        frameLayout.setVisibility(View.VISIBLE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragments.get(random.nextInt(3)));
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

    public void endGame() {
        score++;
        gameChange();
    }

    public void lostGame() {
        gameChange();
    }

    //GameChange uses a random number to determine which minigame is chosen next and changes
    // fragments with fragmentTransaction.
    public void gameChange() {
        updateScore = findViewById(R.id.score);
        updateScore.setText(String.format(Locale.getDefault(), "Score: %d", score));
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragments.get(random.nextInt(3)));
        fragmentTransaction.commit();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        shakerFragment.onSensorChanged(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        shakerFragment.onAccuracyChanged(sensor, accuracy);
    }

    //Accessing the database to add the player name and score, posts the score to twitter then takes
    //the user back to the main activity.
    public void updateScoreBoard() {
        databaseHelper.insertScore(databaseHelper.getWritableDatabase(), "PLAYERSCORE", name, score);
        twitterPost();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void twitterPost() {
        new Thread(() -> {
            Twitter twitter = TwitterFactory.getSingleton();
            try {
                Status status = twitter.updateStatus("" + name + "managed to get a score of " + score + " on ROBOWARE! Can you beat that? ");
                System.out.println("Successfully updated the status to [" + status.getText() + "].");
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
