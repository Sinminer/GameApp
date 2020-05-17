package com.example.educationapp.game;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.educationapp.GameActivity;
import com.example.educationapp.R;

import static com.example.educationapp.R.color.White;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment implements View.OnClickListener {
    Button light1;
    Button light2;
    Button light3;
    Button light4;
    Integer currentLights = 0;
    Integer allLights = 4;

    public LightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_light, container, false);
        light1 = view.findViewById(R.id.light1);
        light2 = view.findViewById(R.id.light2);
        light3 = view.findViewById(R.id.light3);
        light4 = view.findViewById(R.id.light4);

        return view;
    }

    public void onClick(View v) {
        v.setBackgroundColor(getResources().getColor(R.color.Yellow));
        currentLights++;
        if (currentLights.equals(allLights)){
            currentLights = 0;
            v.setBackgroundColor(getResources().getColor(R.color.Black));
            GameActivity.gameActivity.endGame();
        }
    }
}
