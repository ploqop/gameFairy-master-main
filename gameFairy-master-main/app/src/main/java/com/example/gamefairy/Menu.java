package com.example.gamefairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    Button button;
    MediaPlayer mPlayer;
    ImageView stopMusic, startMusic;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mPlayer= MediaPlayer.create(this, R.raw.music1);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });

            play();

            button = findViewById(R.id.newGame);
            stopMusic = findViewById(R.id.stopMusic);
            startMusic = findViewById(R.id.startMusic);
            exit = findViewById(R.id.exit);

            Intent intent = new Intent(this, Game.class);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //stopPlay();
                    startActivity(intent);
                }
            });

            exit.setOnClickListener(view -> {
                finishAndRemoveTask();
            });

            stopMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopPlay();
                }
            });

            startMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    play();
                }
            });
    }

    public void play(){
        mPlayer.start();
    }

    private void stopPlay(){
        mPlayer.stop();
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}