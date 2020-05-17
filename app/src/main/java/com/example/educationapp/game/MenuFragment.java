package com.example.educationapp.game;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.educationapp.GameActivity;
import com.example.educationapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
private int score;
private TextView scoreScreen;
private ImageButton helpButton;
private final String MESSAGE = "Welcome to RoboWare, this game is meant to test your reactions " +
        "and memory by giving you a set time to get through as many minigames as " +
        "possible. Once your time runs out your score will be saved." +
        " Good luck!";
private final String TITLE = "Help";
    private AlertDialog helpAlert;
    public MenuFragment() {
        // Required empty public constructor
    }


    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu,container,false);
        score = GameActivity.SCORE;
        scoreScreen = view.findViewById(R.id.score);
        scoreScreen.setText(String.format("Score: %d", score));
        helpButton = view.findViewById(R.id.help);
        helpButton.setOnClickListener(v -> {
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(getActivity());
            helpAlert = helpBuilder.create();
            helpBuilder.setTitle(TITLE);
            helpBuilder.setMessage(MESSAGE);
            helpBuilder.setPositiveButton("Ok", (dialog, which) -> helpAlert.dismiss());
            helpBuilder.show();
        });
        return view;
    }
}
