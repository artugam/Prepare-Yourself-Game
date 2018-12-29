package com.example.artur.prepareyourself;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class WinnerActivity extends BaseActivity {

    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        desc = findViewById(R.id.winMessageTextView);
        desc.startAnimation(AnimationUtils.loadAnimation(WinnerActivity.this, android.R.anim.fade_in));

        Button playAgainButton = findViewById(R.id.playAgainButton);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changer = new Intent(getApplicationContext(), ChooseClass.class);
                startActivity(changer);

            }
        });
    }
}
