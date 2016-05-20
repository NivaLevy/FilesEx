package com.example.niva.filesex;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView lblLyrics;
    Button cmdPlay;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cmdPlay = (Button)findViewById(R.id.cmdPlay);
        lblLyrics = (TextView)findViewById(R.id.lblLyrics);
        mediaPlayer = MediaPlayer.create(this, R.raw.song);

        cmdPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                showLyrics();
            }
        });


    }

    private void showLyrics() {
        InputStream inputStream = getResources().openRawResource(R.raw.lyrics);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = "", lyrics = "";


        try {
            while ((str = bufferedReader.readLine()) != null){
                lyrics += str + "\n";
            }
            inputStream.close();
            lblLyrics.setText(lyrics);
        }catch (IOException e){
            lyrics = "ERROR";
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}
