package com.example.artur.prepareyourself.Persons.PlayerClasses;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.artur.prepareyourself.Persons.PersonBase;
import com.example.artur.prepareyourself.R;
import com.example.artur.prepareyourself.Skills.Spells.ArrowSpray;
import com.example.artur.prepareyourself.Skills.Weapons.Bow;


public class Archer extends PersonBase {

    private final static String DEFAULT_NAME = "Archer";
    private final static int DEFAULT_HP = 100;
    private final static int DEFAULT_ENERGY = 4;

    public Archer(String imie, int hp, int energy) {
        super(imie, hp, energy);
    }

    public Archer(String imie) {
        super(imie, DEFAULT_HP,  DEFAULT_ENERGY);
    }

    public Archer() {
        super(DEFAULT_NAME, DEFAULT_HP,  DEFAULT_ENERGY);
    }

    @Override
    public void assignSkills()
    {
        this.addSkill(new Bow(3, 2));
        this.addSkill(new ArrowSpray(13, 3));
    }

    public Drawable getThemeImage(Resources resources)
    {
        Drawable drawable = resources.getDrawable( R.drawable.archer, null);
        return drawable;
    }

    @Override
    public String toString() {
        return "Archer{" +
                "imie='" + imie + '\'' +
                ", hp=" + hp +
                ", maxHp=" + maxHp +
                ", energy=" + energy +
                ", maxEnergy=" + maxEnergy +
                '}';
    }
}
