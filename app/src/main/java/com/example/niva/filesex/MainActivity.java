package com.example.niva.filesex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
// background = ResourcesCompat.getDrawable(getResources(), R.drawable.aladdin, null);


    public final static int LITTLE_MERMAID = 1, JUNGLE_BOOK = 2;
    private Button cmdJungleBook, cmdLittleMermaid;
    private Intent intent;
    private FilesOperations operations;
    final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(getApplicationContext(),DisneySong.class);
        operations = new FilesOperations();

        cmdJungleBook = (Button)findViewById(R.id.cmdJungleBook);
        cmdLittleMermaid = (Button)findViewById(R.id.cmdLittleMermaid);

        cmdJungleBook.setOnClickListener(this);
        cmdLittleMermaid.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        if(v.equals(cmdLittleMermaid))
            intent.putExtra("type", LITTLE_MERMAID);
        if(v.equals(cmdJungleBook))
            intent.putExtra("type", JUNGLE_BOOK);
        String nameOfSong = b.getText().toString();
        operations.writeToFile(nameOfSong, getApplicationContext());
        Log.i(TAG, operations.readFromFile(getApplicationContext()));
        startActivity(intent);
    }
}
