package com.example.biocaribe_congresodermatologia_2019.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biocaribe_congresodermatologia_2019.R;

public class LoserActivity extends AppCompatActivity {

    private static int TIME_OUT = 2000; //Time to launch the another activity
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loser);
        mp = MediaPlayer.create(this, R.raw.gameover);
        mp.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoserActivity.this, PreguntaActivity.class);
                startActivity(intent);
                finish();
                mp.stop();
            }
        }, TIME_OUT);


    }
}
