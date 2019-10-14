package com.example.biocaribe_congresodermatologia_2019.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biocaribe_congresodermatologia_2019.R;

public class WinnerActivity extends AppCompatActivity {

    private static int TIME_OUT = 8000; //Time to launch the another activity
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_winner);
        mp = MediaPlayer.create(this, R.raw.aplausos);

        mp.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WinnerActivity.this, PreguntaActivity.class);
                startActivity(intent);
                finish();
                mp.stop();
            }
        }, TIME_OUT);


    }

    public void playSong(View v){
        mp.start();
    }
}
