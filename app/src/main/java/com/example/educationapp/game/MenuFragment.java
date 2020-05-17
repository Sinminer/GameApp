package com.example.educationapp.game;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.educationapp.GameActivity;
import com.example.educationapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
private int score;
private TextView scoreScreen;
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
        return view;
    }
    @SuppressLint("DefaultLocale")
    public void  updateScore(){
        scoreScreen.setText(String.format("Score: %d", score));
    }
}
