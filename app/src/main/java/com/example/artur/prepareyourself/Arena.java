package com.example.artur.prepareyourself;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    public static View currentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        enemy = new Wizard();

        Intent intent = getIntent();
        me = (PersonBase) intent.getSerializableExtra("me");

        actionButton = findViewById(R.id.arenaActionButton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentView = v;


                int selectedActionPos = actionsSelect.getSelectedItemPosition();
                SkillBase currentSkill = me.getSkills().get(selectedActionPos);
                currentSkill.action(getApplicationContext());


                Toast.makeText(v.getContext(),
                        me.getImie() + ": " + currentSkill.getName(),
                        Toast.LENGTH_LONG).show();

                ImageView upView = findViewById(R.id.imageUp);

//                Bitmap bit1 = BitmapFactory.decodeResource(getResources(), R.drawable.wizard);
//                Bitmap bit2= BitmapFactory.decodeResource(getResources(), R.drawable.fireball);
//
//                setBitMaps(bit1, bit2, upView);


//                BitmapGenerator bitmapGen = new BitmapGenerator(bit1, bit2, upView);
//                bitmapGen.run();

                enemy.setHp(enemy.getHp() - currentSkill.getDamage());
                me.setEnergy(me.getEnergy() - currentSkill.getEnergy());
                me.setMana(me.getMana() - currentSkill.getMana());

                setStats();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        enemyTurn();
                    }
                }, 5000);





//                enemy.setHp(enemy.getHp() - currentSkill.getDamage());
//                me.setEnergy(me.getEnergy() - currentSkill.getEnergy());
//                me.setMana(me.getMana() - currentSkill.getMana());
//
//
//                SkillBase randomSkill = enemy.getSkills().get(ThreadLocalRandom.current().nextInt(enemy.getSkills().size()));
//
//
//                Toast.makeText(v.getContext(),
//                        enemy.getImie() + ": " + currentSkill.getName(),
//                        Toast.LENGTH_LONG).show();
//
//
////                Handler handler = new Handler();
////                handler.postDelayed(new Runnable() {
////                    public void run() {
////                        // Actions to do after 10 seconds
////                    }
////                }, 3000);
//
//                randomSkill.action(getApplicationContext());
//
//
//
//                me.setHp(me.getHp() - randomSkill.getDamage());
//                enemy.setMana(enemy.getMana() - randomSkill.getMana());
//                enemy.setEnergy(enemy.getEnergy() - randomSkill.getEnergy());
//
//                setStats();


            }
        });

        this.setStats();
    }

    private void enemyTurn()
    {



        SkillBase randomSkill = enemy.getSkills().get(ThreadLocalRandom.current().nextInt(enemy.getSkills().size()));


        Toast.makeText(currentView.getContext(),
                enemy.getImie() + ": " + randomSkill.getName(),
                Toast.LENGTH_LONG).show();

        randomSkill.action(getApplicationContext());



        me.setHp(me.getHp() - randomSkill.getDamage());
        enemy.setMana(enemy.getMana() - randomSkill.getMana());
        enemy.setEnergy(enemy.getEnergy() - randomSkill.getEnergy());

        setStats();
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
        playerHp.setMax(me.getMaxHp());
        playerHp.setProgress(me.getHp());
        playerHpView.setText(me.getHp() + "");


        ProgressBar playerEp = findViewById(R.id.viewDownEnergy);
        TextView playerEpView = findViewById(R.id.playerEpTextView);
        playerEp.setMax(me.getMaxEnergy());
        playerEp.setProgress(me.getEnergy());
        playerEpView.setText(me.getEnergy() + "");


        ProgressBar playerMp = findViewById(R.id.viewDownMana);
        TextView playerMpView = findViewById(R.id.playerMpTextView);
        playerMp.setMax(me.getMaxMana());
        playerMp.setProgress(me.getMana());
        playerMpView.setText(me.getMana() + "");


        TextView viewDown = findViewById(R.id.viewDown);
        viewDown.setBackground((me.getThemeImage(getResources())));

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
        ProgressBar upViewHp = findViewById(R.id.viewUpHp);
        upViewHp.setMax(100);
        upViewHp.setProgress(this.enemy.getHp());
//
        TextView hpNumber = findViewById(R.id.enemyHp);
        hpNumber.setText(this.enemy.getHp() + " " );

        ImageView viewUp = findViewById(R.id.imageUp);
        viewUp.setBackground((enemy.getThemeImage(getResources())));
    }




























    public static int amount = 255;
    public Bitmap bitmap1;
    public Bitmap bitmap2;
    public ImageView img;
    public static AppCompatActivity app;

    public void setBitMaps(Bitmap bitmap1, Bitmap bitmap2, ImageView img)
    {
        this.bitmap1 = bitmap1;
        this.bitmap2 = bitmap2;
        this.img = img;
        this.run();
    }

    public void run()
    {
        final long changeTime = 20;

        final Thread thread = new Thread(){
            @Override
            public void run() {

                while (!isInterrupted())
                {
                    try{
                        Thread.sleep(changeTime);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                amount -= 10;
                                amount = amount < 0 ? 0 : amount;
                                Bitmap mergedImg = overlayBitmapToCenter(bitmap1, bitmap2, amount);
                                img.setImageBitmap(mergedImg);

                                if(amount == 0)
                                {
                                    interrupt();
                                }
                            }
                        });

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
    }

    public Bitmap overlayBitmapToCenter(Bitmap bitmap1, Bitmap overlayBitmap, int alpha ) {

        Paint paint = new Paint();
        paint.setAlpha(alpha);
        Bitmap bmOverlay = Bitmap.createBitmap(this.img.getWidth(), this.img.getWidth(), bitmap1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bitmap1, null, new Rect(0,0,this.img.getWidth(),this.img.getHeight()), null);
        canvas.drawBitmap(overlayBitmap, null, new Rect(0,0,bitmap1.getWidth(),bitmap1.getHeight()), paint);
        return bmOverlay;

    }

}
