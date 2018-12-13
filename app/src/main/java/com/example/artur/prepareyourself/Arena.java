package com.example.artur.prepareyourself;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.artur.prepareyourself.Persons.PersonBase;
import com.example.artur.prepareyourself.Persons.PlayerClasses.Wizard;
import com.example.artur.prepareyourself.Skills.SkillBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Arena extends AppCompatActivity {

    private Spinner actionsSelect;
    private Button actionButton;

    public static PersonBase me;
    public static PersonBase enemy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        enemy = new Wizard();
        enemy.assignSkills();
        Intent intent = getIntent();
        me = (PersonBase) intent.getSerializableExtra("me");

        actionButton = findViewById(R.id.arenaActionButton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedActionPos = actionsSelect.getSelectedItemPosition();
                SkillBase currentSkill = me.getSkills().get(selectedActionPos);
                currentSkill.action(getApplicationContext());

                findViewById(R.id.viewUp);
//                BitmapGenerator bitmapGen = new BitmapGenerator();

                enemy.setHp(enemy.getHp() - currentSkill.getDamage());
                me.setEnergy(me.getEnergy() - currentSkill.getEnergy());
                me.setMana(me.getMana() - currentSkill.getMana());


                SkillBase randomSkill = enemy.getSkills().get(ThreadLocalRandom.current().nextInt(enemy.getSkills().size()));
                randomSkill.action(getApplicationContext());

                me.setHp(me.getHp() - randomSkill.getDamage());
                enemy.setMana(enemy.getMana() - randomSkill.getMana());
                enemy.setEnergy(enemy.getEnergy() - randomSkill.getEnergy());

                setStats();


            }
        });

        this.setStats();
    }


    private void setStats()
    {
        this.setMeStats();
        this.setEnemyStats();
    }

    private void setMeStats()
    {

        me.assignSkills();

        Button hp = findViewById(R.id.viewDownHp);
        Button energy = findViewById(R.id.viewDownEnergy);
        Button mana = findViewById(R.id.viewDownMana);

        hp.setText(me.getHp() + "");
        energy.setText(me.getEnergy() + "");
        mana.setText(me.getMana() + "");

        TextView viewDown = findViewById(R.id.viewDown);
        viewDown.setBackground((me.getThemeImage(getResources())));
        viewDown.setAlpha(new Float(0.5));


        actionsSelect = findViewById(R.id.arenaActionSelectSpinner);
        List<String> list = new ArrayList<>();

        for(int i =0; i < me.getSkills().size(); i++)
        {
            list.add(me.getSkills().get(i).getName());
        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actionsSelect.setAdapter(dataAdapter);

    }

    private void setEnemyStats()
    {
        Button upViewHp = findViewById(R.id.viewUpHp);
        Button upViewEnergy = findViewById(R.id.viewUpEnergy);
        Button upViewMana =  findViewById(R.id.viewUpMana);

        upViewHp.setText(this.enemy.getHp() + "");
        upViewEnergy.setText(this.enemy.getEnergy() + "");
        upViewMana.setText(this.enemy.getMana() + "");

        TextView viewUp = findViewById(R.id.viewUp);
        viewUp.setBackground((enemy.getThemeImage(getResources())));
    }
}
