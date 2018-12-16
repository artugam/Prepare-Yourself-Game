package com.example.artur.prepareyourself;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.artur.prepareyourself.Helpers.Validators.Base;
import com.example.artur.prepareyourself.Persons.PersonBase;
import com.example.artur.prepareyourself.Persons.PlayerClasses.Warrior;
import com.example.artur.prepareyourself.Persons.PlayerClasses.Wizard;

public class ChooseClass extends AppCompatActivity {

    private Spinner playerSelect;

    private PersonBase player;

    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);

        addOnSpinnerSelectListener();

        final Button selectPlayer = findViewById(R.id.submitPlayerSelectButton);

        selectPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!validateInputs())
                {
                    return;
                }


                Intent changer = new Intent(getApplicationContext(), Profile.class);

                String playerClass = playerSelect.getSelectedItem().toString();

                PersonBase player = getPlayerClass(playerClass);

                changer.putExtra("me", player);
                startActivity(changer);
            }
        });
    }

    private boolean validateInputs()
    {
        EditText playerNameField = findViewById(R.id.playerNameInputText);
        playerName = playerNameField.getText().toString();

        if(Base.isEmptyString(playerName))
        {
            return false;
        }

        return true;
    }

    private PersonBase getPlayerClass(String playerClass)
    {
        switch (playerClass)
        {
            case "Warrior":
                return new Warrior(playerName);
            case "Wizard":
                return new Wizard(playerName);
//            case "Scoundriel":
//                return new Scoundriel(playerName);
        }
        return new Warrior();
    }



    public void addOnSpinnerSelectListener()
    {
        this.playerSelect = findViewById(R.id.playerSelectSpinner);
        playerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_LONG).show();

                String selectedClass =  parent.getItemAtPosition(position).toString();


                int idTest = getResources().getIdentifier(selectedClass.toLowerCase(), "drawable", "com.example.artur.prepareyourself");

                Drawable drawable = getResources().getDrawable(idTest, null);
                getWindow().setBackgroundDrawable(drawable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
