package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.educationapp.game.HangmanFragment;
import com.example.educationapp.game.LightFragment;
import com.example.educationapp.game.ShakerFragment;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener, View.OnClickListener {
    FragmentManager fragmentManager;
    HangmanFragment hangmanFragment;
    LightFragment lightFragment;
    FragmentTransaction fragmentTransaction;
    ShakerFragment shakerFragment;
    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random random = new Random();

        hangmanFragment = new HangmanFragment();
        shakerFragment = new ShakerFragment();
        lightFragment = new LightFragment();
        fragments = new ArrayList<>(4);
        fragments.add(hangmanFragment);
        fragments.add(shakerFragment);
        fragments.add(lightFragment);
        setContentView(R.layout.activity_game);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
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


}
