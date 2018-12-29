package com.example.artur.prepareyourself.Skills.Weapons;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.artur.prepareyourself.R;
import com.example.artur.prepareyourself.Skills.SkillBase;

public class OneHandSword extends SkillBase {

    private final static int DAMAGE_MULTIPLER = 1;

    protected static String name = "One Hand Sword";

    public OneHandSword(int damage, int energy) {

        super(name, damage * DAMAGE_MULTIPLER, energy);
    }


    public int getMusic()
    {
        return R.raw.weapon_sword_attack;
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
