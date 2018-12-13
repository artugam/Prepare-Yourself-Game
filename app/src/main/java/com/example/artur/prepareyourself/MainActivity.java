package com.example.artur.prepareyourself;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMusic();

        Button startGameButton = (Button)findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changer = new Intent(getApplicationContext(), ChooseClass.class);

                startActivity(changer);
            }
        });
    }


    void setMusic()
    {
        MediaPlayer sing = MediaPlayer.create(getApplicationContext(), R.raw.song);
        sing.start();
    }
}
