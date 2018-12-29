package com.example.artur.prepareyourself;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends BaseActivity {


    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        desc = findViewById(R.id.gameOverMessageTextView);
        desc.startAnimation(AnimationUtils.loadAnimation(GameOverActivity.this, android.R.anim.fade_in));

        Button startGameButton = findViewById(R.id.playAgainButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changer = new Intent(getApplicationContext(), ChooseClass.class);
                startActivity(changer);

            }
        });
    }
}
