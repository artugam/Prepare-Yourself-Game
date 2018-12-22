package com.example.artur.prepareyourself;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.artur.prepareyourself.Persons.PersonBase;

public class Profile extends AppCompatActivity {

    public static PersonBase me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();

        me = (PersonBase) intent.getSerializableExtra("me");

        this.setDetails();


        Button fightButton = findViewById(R.id.fightButton);
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent changer = new Intent(getApplicationContext(), Arena.class);

                changer.putExtra("me", me);
                startActivity(changer);
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
        welcomeText.setText(welcomeText.getText() + " " + me.getImie());

        getWindow().setBackgroundDrawable(me.getThemeImage(getResources()));
        return this;
    }

    private Profile setResources()
    {
        ProgressBar playerHp = findViewById(R.id.profileHpButton);
        TextView playerHpView = findViewById(R.id.profileHpTextView);
        playerHp.setMax(me.getMaxHp());
        playerHp.setProgress(me.getHp());
        playerHpView.setText(me.getHp() + "");

        ProgressBar playerEp = findViewById(R.id.profileEpButton);
        TextView playerEpView = findViewById(R.id.profileEpTextView);
        playerEp.setMax(me.getMaxEnergy());
        playerEp.setProgress(me.getEnergy());
        playerEpView.setText(me.getEnergy() + "");


        ProgressBar playerMp = findViewById(R.id.profilePmButton);
        TextView playerMpView = findViewById(R.id.profileMpTextView);
        playerMp.setMax(me.getMaxMana());
        playerMp.setProgress(me.getMana());
        playerMpView.setText(me.getMana() + "");

        return this;
    }
}
