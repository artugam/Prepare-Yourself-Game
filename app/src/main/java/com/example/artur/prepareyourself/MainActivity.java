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

//                toRemoveAnimation();

            }
        });
    }

//    public static TextView desc;

    public void toRemoveAnimation()
    {
//        desc = findViewById(R.id.animationTest);
//        desc.setVisibility(View.VISIBLE);
//        desc.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                desc.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right));
//                desc.setVisibility(View.INVISIBLE);
//            }
//        }, 1000);



    }


    void setMusic()
    {
        MediaPlayer sing = MediaPlayer.create(getApplicationContext(), R.raw.song);
        sing.start();
    }
}
