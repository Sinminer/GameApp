package com.example.educationapp.game;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;

import com.example.educationapp.R;

public class LetterAdapter extends BaseAdapter {
    private String[] alphabet;
    private LayoutInflater layoutInflater;


    public LetterAdapter(Context context) {
        alphabet = new String[26];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = "" + (char) (i + 'A');
        }
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return alphabet.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            button = (Button) layoutInflater.inflate(R.layout.letter, parent, false);
        } else {
            button = (Button) convertView;
        }
        button.setText(alphabet[position]);
        return button;
    }
}
