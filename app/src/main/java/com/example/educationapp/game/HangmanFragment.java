package com.example.educationapp.game;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.educationapp.GameActivity;
import com.example.educationapp.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */

//Initialising global variables used in the hangmanFragment also implementing onItemClickListener
public class HangmanFragment extends Fragment implements AdapterView.OnItemClickListener {
    private int numParts = 6;
    private int currPart;
    private int numChars;
    private int numCorr;

    private String currWord;

    private TextView[] charViews;

    private ImageView[] bodyParts;
    private String[] words;

    private LinearLayout wordLayout;

    private GridView letterGrid;
    private LetterAdapter letterAdapter;


    public HangmanFragment() {
        // Required empty public constructor
    }


    @Override
    //Assigning bodyParts to the view and initialising the letter grid with the letter adapter.
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hangman, container, false);

        numCorr = 0;
        currWord = "";
        currPart = 0;

        letterGrid = view.findViewById(R.id.letters);
        letterGrid.setOnItemClickListener(this);
        letterAdapter = new LetterAdapter(view.getContext());
        letterGrid.setAdapter(letterAdapter);


        bodyParts = new ImageView[numParts];
        words = getResources().getStringArray(R.array.words);

        bodyParts[0] = view.findViewById(R.id.head);
        bodyParts[1] = view.findViewById(R.id.body);
        bodyParts[2] = view.findViewById(R.id.arm1);
        bodyParts[3] = view.findViewById(R.id.arm2);
        bodyParts[4] = view.findViewById(R.id.leg1);
        bodyParts[5] = view.findViewById(R.id.leg2);

        hideParts();

        wordLayout = view.findViewById(R.id.word);

        makeGame();

        return view;

    }

    //Make Game method creates a new game, remove all old views if the same fragment is used again
    //and grabs a new word out of the string array and sets all the letters back to white for reuse.
    private void makeGame() {
        Random random = new Random();

        letterGrid.setAdapter(letterAdapter);

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

            wordLayout.addView(charViews[c]);
        }
    }

    //LetterPressed method is called when a letter is called, it determines which letter is picked
    //if the correct letter is chosen adds to the numCorr counter and disables said letter,
    //if the wrong letter is chosen a body part comes up on the board. Once either the word is found
    //or all the body parts are visible the game ends.
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
        } else if (currPart < numParts) {
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        } else {
            currPart = 0;
            hideParts();
            makeGame();
            GameActivity.gameActivity.lostGame();
        }
        if (numCorr == numChars) {
            currPart = 0;
            hideParts();
            makeGame();
            GameActivity.gameActivity.endGame();
        }
    }

    private void hideParts() {
        for (int p = 0; p < numParts; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);
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
