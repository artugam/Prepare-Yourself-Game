package com.example.artur.prepareyourself;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.artur.prepareyourself.System.Music;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setMusic();

        Button startGameButton = findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changer = new Intent(getApplicationContext(), ChooseClass.class);
                startActivity(changer);

            }
        });
    }

    private void setMusic()
    {
        Music.createMusic(getApplicationContext(), R.raw.song);
        Music.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Music.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Music.pause();
    }


}
