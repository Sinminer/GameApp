package com.example.educationapp.game;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.educationapp.GameActivity;
import com.example.educationapp.R;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangmanFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ImageView[] bodyParts;
    private int numParts = 6;
    private int currPart;
    private int numChars;
    private int numCorr;
    private String currWord;
    private String[] words;
    private Random random;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private GridView letterGrid;
    private LetterAdapter letterAdapater;


    public HangmanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hangman, container, false);
        letterGrid = view.findViewById(R.id.letters);
        letterAdapater = new LetterAdapter(view.getContext());
        letterGrid.setAdapter(letterAdapater);
        random = new Random();
        letterGrid.setOnItemClickListener(this);
        currWord = "";
        currPart = 0;

        numCorr = 0;
        bodyParts = new ImageView[numParts];
        words = getResources().getStringArray(R.array.words);
        bodyParts[0] = view.findViewById(R.id.head);
        bodyParts[1] = view.findViewById(R.id.body);
        bodyParts[2] = view.findViewById(R.id.arm1);
        bodyParts[3] = view.findViewById(R.id.arm2);
        bodyParts[4] = view.findViewById(R.id.leg1);
        bodyParts[5] = view.findViewById(R.id.leg2);
        for (int p = 0; p < numParts; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);
        }

        wordLayout = view.findViewById(R.id.word);

        makeGame();
        return view;

    }

    private void makeGame() {

        currWord = words[random.nextInt(words.length)];
        charViews = new TextView[currWord.length()];
        numChars = currWord.length();
        wordLayout.removeAllViews();
        for (int c = 0; c < currWord.length(); c++) {
            charViews[c] = new TextView(this.getContext());
            charViews[c].setText(String.format("%s", currWord.charAt(c)));

            charViews[c].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.WHITE);
            charViews[c].setBackgroundResource(R.drawable.letter_bg);
            //add to layout
            wordLayout.addView(charViews[c]);
        }
    }


//    public void disableBtns() {
//        int numLetters = letterGrid.getChildCount();
//        for (int l = 0; l < numLetters; l++) {
//            letterGrid.getChildAt(l).setEnabled(false);
//        }
//    }

    public void letterPressed(View view) {

        String ltr = ((TextView) view).getText().toString();
        char letterChar = ltr.charAt(0);
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.letter_down);

        boolean correct = false;
        for (int k = 0; k < currWord.length(); k++) {
            if (currWord.charAt(k) == letterChar) {
                correct = true;
                numCorr++;
                charViews[k].setTextColor(Color.BLACK);
            }
        }
        if (correct) {
            //correct guess
        } else if (currPart < numParts) {
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        } else {
            makeGame();
            GameActivity.gameActivity.lostGame();
            onDestroy();
        }
        if (numCorr == numChars) {
            makeGame();
            GameActivity.gameActivity.endGame();
            onDestroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        letterPressed(view);
    }
}
