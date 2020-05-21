package com.example.educationapp.game;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.educationapp.GameActivity;
import com.example.educationapp.MainActivity;
import com.example.educationapp.R;
import com.example.educationapp.SettingsActivity;

import java.util.Locale;


public class MenuFragment extends Fragment {
    private static final String MESSAGE = "Welcome to RoboWare, this game is meant to test your reactions " +
            "and memory by giving you a set time to get through as many minigames as " +
            "possible. Once your time runs out your score will be saved." +
            " Good luck!";
    private static final String TITLE = "Help";

    private TextView timer;
    private int time;

    private boolean shutdown = false;
    private Button startButton;
    private AlertDialog helpAlert;
    //Timer that takes the set time and counts down, at the end of the timer the thread stops and
    //the score is updated.
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (!shutdown) {
                timer.setText(String.format(Locale.getDefault(), "Time:%d", time));
                timerHandler.postDelayed(timerRunnable, 1000);
                time -= 1;
                if (time == -1) {
                    shutdown = true;
                    GameActivity.gameActivity.updateScoreBoard();
                    startButton.setEnabled(true);
                }
            }
        }
    };

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        int score = GameActivity.score;


        TextView scoreScreen = view.findViewById(R.id.score);
        timer = view.findViewById(R.id.timer);
        ImageButton helpButton = view.findViewById(R.id.help);
        startButton = view.findViewById(R.id.startButton);

        chooseDifficultly(SettingsActivity.level);

        scoreScreen.setText(String.format(Locale.getDefault(), "Score: %d", score));

        startButton.setOnClickListener(v -> {
            GameActivity.gameActivity.startGame();
            timerRunnable.run();
            startButton.setEnabled(false);
        });
        //HelpButton creates a dialog for the player to read.
        helpButton.setOnClickListener(v -> {
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(getActivity());
            helpAlert = helpBuilder.create();
            helpBuilder.setTitle(TITLE);
            helpBuilder.setMessage(MESSAGE);
            helpBuilder.setPositiveButton("Ok", (dialog, which) -> helpAlert.dismiss());
            helpBuilder.show();
        });

        ImageButton backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
        return view;
    }

    private void chooseDifficultly(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                time = 180;
                break;
            case MEDIUM:
                time = 120;
                break;
            case HARD:
                time = 60;
                break;
        }
    }
}
