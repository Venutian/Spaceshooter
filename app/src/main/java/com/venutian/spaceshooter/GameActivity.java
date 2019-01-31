package com.venutian.spaceshooter;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    Game _game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _game = new Game(this);
       // _game.setBackgroundResource(R.drawable.background_ingame);
        setContentView(_game);



    }

    @Override
    protected void onPause(){
        super.onPause();
        _game.onPause();
    }

    @Override
    protected void onResume(){
       super.onResume();
        _game.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        _game.onDestroy();
    }
}
