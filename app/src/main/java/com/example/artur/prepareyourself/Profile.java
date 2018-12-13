package com.example.artur.prepareyourself;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
        Button HP = findViewById(R.id.profileHpButton);
        HP.setText(me.getHp() + "");

        Button EP = findViewById(R.id.profileEpButton);
        EP.setText(me.getEnergy() + "");

        Button PM = findViewById(R.id.profilePmButton);
        PM.setText(me.getMana() + "");

        return this;
    }
}
