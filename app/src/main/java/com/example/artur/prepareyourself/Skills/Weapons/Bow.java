package com.example.artur.prepareyourself.Skills.Weapons;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.artur.prepareyourself.R;
import com.example.artur.prepareyourself.Skills.SkillBase;

public class Bow extends SkillBase {

    private final static int DAMAGE_MULTIPLER = 1;

    protected static String name = "Bow";

    public Bow(int damage, int energy, int mana) {

        super(name, damage * DAMAGE_MULTIPLER, energy, mana);
    }


    public int getMusic()
    {
        return R.raw.weapon_bow_attack;
    }

    public int getEffectImage()
    {
        return 0;
    }

    @Override
    public void action(Context appContext) {

        MediaPlayer spellMusic = MediaPlayer.create(appContext, getMusic());
        spellMusic.start();
    }

    @Override
    public String toString() {
        return "OneHandSword{" +
                "damage=" + damage +
                ", energy=" + energy +
                ", name='" + name + '\'' +
                '}';
    }
}
