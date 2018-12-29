package com.example.artur.prepareyourself.Skills.Spells;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.artur.prepareyourself.R;
import com.example.artur.prepareyourself.Skills.SkillBase;

public class JumpAttack extends SkillBase {

    private final static int DAMAGE_MULTIPLER = 3;

    protected static String name = "JumpAttack";

    public JumpAttack(int damage, int energy) {

        super(name, damage * DAMAGE_MULTIPLER, energy);
    }

    public int getMusic()
    {
        return R.raw.spells_jump_attack;
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
        return "JumpAttack{" +
                "damage=" + damage +
                ", energy=" + energy +
                ", name='" + name + '\'' +
                '}';
    }
}
