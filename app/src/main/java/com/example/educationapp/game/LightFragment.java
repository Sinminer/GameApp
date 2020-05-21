package com.example.educationapp.game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.educationapp.GameActivity;
import com.example.educationapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
//Assigning global variables for lightFragment usage
public class LightFragment extends Fragment implements View.OnClickListener {
    private Integer currentLights = 0;
    private ArrayList<Button> lights;

    public LightFragment() {
        // Required empty public constructor
    }

    //Assigning light variables to the view and then adding them to a ButtonArray
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_light, container, false);

        Button light1 = view.findViewById(R.id.light1);
        Button light2 = view.findViewById(R.id.light2);
        Button light3 = view.findViewById(R.id.light3);
        Button light4 = view.findViewById(R.id.light4);

        lights = new ArrayList<>(4);

        lights.add(light1);
        lights.add(light2);
        lights.add(light3);
        lights.add(light4);
        return view;
    }

    //An onclick method used to detect which light is being pressed in the fragment then changes
    // the colour of the light to yellow, once all lights are on the game sets all lights back to
    //black and the fragment destroys itself.
    public void onClick(View v) {
        v.setBackgroundColor(getResources().getColor(R.color.Yellow));
        currentLights++;
        Integer allLights = 4;
        if (currentLights.equals(allLights)) {
            currentLights = 0;
            GameActivity.gameActivity.endGame();
            setAllLightsOn();
            onDestroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    private void setAllLightsOn() {
        for (int i = 0; i < 4; i++) {
            lights.get(i).setBackgroundColor(getResources().getColor(R.color.Black));
        }
    }
}
