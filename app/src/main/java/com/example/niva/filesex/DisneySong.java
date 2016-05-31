package com.example.niva.filesex;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;

public class DisneySong extends AppCompatActivity {
    private TextView lblLyrics;
    private Button cmdPlay;
    private MediaPlayer mediaPlayer;
    private FilesOperations operations;
    private InputStream inputStream;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disney_song);

        layout = (LinearLayout)findViewById(R.id.layout);
        int type = getIntent().getIntExtra("type", MainActivity.JUNGLE_BOOK);
        switch (type){
            case MainActivity.JUNGLE_BOOK:
                mediaPlayer = MediaPlayer.create(this, R.raw.song);
                inputStream = getResources().openRawResource(R.raw.lyrics);
                layout.setBackground( ResourcesCompat.getDrawable(getResources(), R.drawable.jungle, null));
                break;
            case MainActivity.LITTLE_MERMAID:
                mediaPlayer = MediaPlayer.create(this, R.raw.underthesea);
                inputStream = getResources().openRawResource(R.raw.underthesealyrics);
                layout.setBackground( ResourcesCompat.getDrawable(getResources(), R.drawable.mermaid, null));
                break;
        }
        operations = new FilesOperations();
        cmdPlay = (Button)findViewById(R.id.cmdPlay);
        lblLyrics = (TextView)findViewById(R.id.lblLyrics);

        operations = new FilesOperations();
        cmdPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                lblLyrics.setText(operations.getLyrics(inputStream));
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}
