package com.example.educationapp.game;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.educationapp.GameActivity;
import com.example.educationapp.MainActivity;
import com.example.educationapp.R;

import org.w3c.dom.Text;

import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    private int score;
    private TextView scoreScreen;
    private ImageButton helpButton;
    private Button startButton;
    public static final String MESSAGE = "Welcome to RoboWare, this game is meant to test your reactions " +
            "and memory by giving you a set time to get through as many minigames as " +
            "possible. Once your time runs out your score will be saved." +
            " Good luck!";
    public static final String TITLE = "Help";
    private AlertDialog helpAlert;
    ImageButton backButton;
    FrameLayout frameLayout;
    TextView timer;
    int time = 5;
    boolean shutdown = false;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if(!shutdown) {
                timer.setText(String.format("Time:%d", time));
                timerHandler.postDelayed(timerRunnable, 1000);
                time -= 1;
                if (time == -1) {
                    shutdown = true;
                    GameActivity.gameActivity.updateScoreBoard();
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
        score = GameActivity.SCORE;
        frameLayout = view.findViewById(R.id.fragment_container);
        scoreScreen = view.findViewById(R.id.score);
        scoreScreen.setText(String.format("Score: %d", score));
        helpButton = view.findViewById(R.id.help);
        startButton = view.findViewById(R.id.startButton);
        timer = view.findViewById(R.id.timer);
        startButton.setOnClickListener(v -> {
            GameActivity.gameActivity.startGame();
            timerRunnable.run();
        });

        helpButton.setOnClickListener(v -> {
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(getActivity());
            helpAlert = helpBuilder.create();
            helpBuilder.setTitle(TITLE);
            helpBuilder.setMessage(MESSAGE);
            helpBuilder.setPositiveButton("Ok", (dialog, which) -> helpAlert.dismiss());
            helpBuilder.show();
        });

        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
        return view;
    }
}
