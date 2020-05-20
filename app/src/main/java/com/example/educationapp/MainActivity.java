package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.educationapp.database.DatabaseHelper;


public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private static final String NAME = "NAME";
    EditText nameView;
    String name;
    Button highScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        highScores = findViewById(R.id.highScores);
        nameView = findViewById(R.id.textView);
        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener((v) -> openGame());
        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener((v) -> openSettings());
        highScores.setOnClickListener((v -> openHighScores()));
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        database.isOpen();
    }
    public void openSettings(){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
    public void openGame(){
        name = nameView.getText().toString();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(NAME,name);
        startActivity(intent);
    }

    public void openHighScores(){

        Intent intent = new Intent(this,ScoresActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE){
            name = nameView.getText().toString();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
