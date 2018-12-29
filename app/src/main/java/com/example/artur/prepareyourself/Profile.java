package com.example.artur.prepareyourself;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.artur.prepareyourself.Persons.PersonBase;

public class Profile extends BaseActivity {

    protected PersonBase player;
    protected int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();

        player = (PersonBase) intent.getSerializableExtra("player");
        level = (int) intent.getSerializableExtra("level");

        Log.d("level", level + "");

        this.setDetails();

        Button fightButton = findViewById(R.id.fightButton);
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent changer = new Intent(getApplicationContext(), Arena.class);

                changer.putExtra("player", player);
                changer.putExtra("level", level);

                startActivity(changer);
                finish();
            }
        });

    }

    private void setDetails()
    {
        this.setBackgroundImageAndName().setResources();
    }

    private Profile setBackgroundImageAndName()
    {
        TextView welcomeText = findViewById(R.id.profileWelcomeTextView);
        welcomeText.setText(welcomeText.getText() + " " + player.getImie());

        TextView profileLevel = findViewById(R.id.profileNextLevelTextView);
        profileLevel.setText(profileLevel.getText() + " " + this.level);

        getWindow().setBackgroundDrawable(player.getThemeImage(getResources()));
        return this;
    }

    private Profile setResources()
    {
        ProgressBar playerHp = findViewById(R.id.profileHpButton);
        TextView playerHpView = findViewById(R.id.profileHpTextView);
        playerHp.setMax(player.getMaxHp());
        playerHp.setProgress(player.getHp());
        playerHpView.setText(player.getHp() + "");

        player.setEnergy(player.getENERGY_RECOVER());
        ProgressBar playerEp = findViewById(R.id.profileEpButton);
        TextView playerEpView = findViewById(R.id.profileEpTextView);
        playerEp.setMax(player.getMaxEnergy());
        playerEp.setProgress(player.getEnergy());
        playerEpView.setText(player.getEnergy() + "");

        return this;
    }
}
