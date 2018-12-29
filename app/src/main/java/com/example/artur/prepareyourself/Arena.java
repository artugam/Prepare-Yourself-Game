package com.example.artur.prepareyourself;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artur.prepareyourself.Persons.PersonBase;
import com.example.artur.prepareyourself.Persons.PlayerClasses.Archer;
import com.example.artur.prepareyourself.Persons.PlayerClasses.Warrior;
import com.example.artur.prepareyourself.Persons.PlayerClasses.Wizard;
import com.example.artur.prepareyourself.Skills.SkillBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Arena extends BaseActivity {

    private Spinner actionsSelect;
    private Button actionButton;
    private Button endTurn;

    public static TextView desc;
    public static PersonBase player;
    public static PersonBase enemy;

    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        this.setBasics();
        this.setStats();

        endTurn = findViewById(R.id.arenaPlayerEndTurn);
        actionButton = findViewById(R.id.arenaActionButton);

        this.battleStarts();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTurnStart();
            }
        });

        endTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enemyTurnStart();
            }
        });
    }


    private void setBasics()
    {
        Intent intent = getIntent();
        this.level = (Integer) intent.getSerializableExtra("level");
        player =    (PersonBase) intent.getSerializableExtra("player");
        enemy = this.getEnemy();
    }

    private void battleStarts()
    {
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                setMainFightDescription(R.string.arena_battle_begins);

            }
        }, 1000);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                setMainFightDescription(R.string.arena_your_turn);
            }
        }, 3000);
    }

    private void enemyTurnStart()
    {
        actionButton.setClickable(false);
        endTurn.setVisibility(View.INVISIBLE);

        setMainFightDescription(R.string.arena_enemy_turn);

        Handler handler1 = new Handler();
        handler1.postDelayed(enemyTurnRunnable, 1000);
    }

    private void enemyTurnPostActions()
    {
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                nextTurn();
                setMainFightDescription(R.string.arena_your_turn);
                actionButton.setClickable(true);
                endTurn.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    public void myTurnStart()
    {
        int selectedActionPos = actionsSelect.getSelectedItemPosition();
        SkillBase currentSkill = player.getSkills().get(selectedActionPos);
        currentSkill.action(getApplicationContext());

        Toast.makeText(getApplicationContext(),
                player.getImie() + ": " + currentSkill.getName(),
                Toast.LENGTH_LONG).show();

        enemy.setHp(enemy.getHp() - currentSkill.getDamage());
        player.setEnergy(player.getEnergy() - currentSkill.getEnergy());

        setStats();

        if(player.getEnergy() < 1 && enemy.isAlive())
        {
            enemyTurnStart();
        }
    }


    private void setMainFightDescription (int textId)
    {
        String text = getString(textId);
        desc = findViewById(R.id.mainFightDescriptionTextView);
        desc.setText(text);
        desc.setVisibility(View.VISIBLE);
        desc.startAnimation(AnimationUtils.loadAnimation(Arena.this, android.R.anim.slide_in_left));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                desc.startAnimation(AnimationUtils.loadAnimation(Arena.this, android.R.anim.slide_out_right));
                desc.setVisibility(View.INVISIBLE);
            }
        }, 1000);
    }

    private void nextTurn()
    {
        player.nextTurn();
        enemy.nextTurn();
        setStats();
    }

    private Handler enemyHandler = new Handler();
    private Runnable enemyTurnRunnable = new Runnable() {
        @Override
        public void run() {

            SkillBase randomSkill = enemy.getSkills().get(ThreadLocalRandom.current().nextInt(enemy.getSkills().size()));

            if(randomSkill.getEnergy() > enemy.getEnergy())
            {
                enemyHandler.removeCallbacks(this);
                enemyTurnPostActions();
                return;
            }

            com.example.artur.prepareyourself.Helpers.Toast.showMessage(getApplicationContext(), enemy.getImie() + ": " + randomSkill.getName());
            randomSkill.action(getApplicationContext());

            player.setHp(player.getHp() - randomSkill.getDamage());
            enemy.setEnergy(enemy.getEnergy() - randomSkill.getEnergy());

            setStats();
            enemyHandler.postDelayed(this, 2000);
        }
    };


    public PersonBase getEnemy()
    {
        switch (this.level)
        {
            case 1:
                return new Wizard();
            case 2:
                return new Warrior();
        }
        return new Archer();
    }

    private void setStats()
    {
        this.setMeStats();
        this.setEnemyStats();
    }

    private void setMeStats()
    {
        ProgressBar playerHp = findViewById(R.id.viewDownHp);
        TextView playerHpView = findViewById(R.id.playerHpTextView);
        playerHp.setMax(player.getMaxHp());
        playerHp.setProgress(player.getHp());
        playerHpView.setText(player.getHp() + "");


        ProgressBar playerEp = findViewById(R.id.viewDownEnergy);
        TextView playerEpView = findViewById(R.id.playerEpTextView);
        playerEp.setMax(player.getMaxEnergy());
        playerEp.setProgress(player.getEnergy());
        playerEpView.setText(player.getEnergy() + "");

        TextView viewDown = findViewById(R.id.viewDown);
        viewDown.setBackground((player.getThemeImage(getResources())));

        actionsSelect = findViewById(R.id.arenaActionSelectSpinner);
        List<String> list = new ArrayList<>();

        String skillName;
        SkillBase skill;
        for(int i =0; i < player.getSkills().size(); i++)
        {
            skill = player.getSkills().get(i);
            skillName = skill.getName() + " EP: " + skill.getEnergy();
            list.add(skillName);
        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actionsSelect.setAdapter(dataAdapter);

        if(!player.isAlive())
        {
            this.gameOver();
        }

    }

    private void gameOver()
    {
        Intent changer = new Intent(getApplicationContext(), GameOverActivity.class);
        startActivity(changer);

        finish();
    }

    private void goWinnerActivity()
    {
        Intent changer = new Intent(getApplicationContext(), WinnerActivity.class);
        startActivity(changer);

        finish();
    }

    private void setEnemyStats()
    {
        if(!this.enemy.isAlive())
        {
            if(this.level == 3)
            {
                this.goWinnerActivity();
                return;
            }
            this.goProfile();
            return;
        }
        ProgressBar upViewHp = findViewById(R.id.viewUpHp);
        upViewHp.setMax(this.enemy.getMaxHp());
        upViewHp.setProgress(this.enemy.getHp());

        TextView hpNumber = findViewById(R.id.enemyHp);
        hpNumber.setText(this.enemy.getHp() + " " );

        ImageView viewUp = findViewById(R.id.imageUp);
        viewUp.setBackground((enemy.getThemeImage(getResources())));
    }

    private void goProfile()
    {
        Intent changer = new Intent(getApplicationContext(), Profile.class);

        changer.putExtra("player", player);
        changer.putExtra("level", this.level + 1);
        startActivity(changer);

        finish();
    }
}
