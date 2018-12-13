package com.example.artur.prepareyourself.Persons.PlayerClasses;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.artur.prepareyourself.Persons.PersonBase;
import com.example.artur.prepareyourself.R;
import com.example.artur.prepareyourself.Skills.Weapons.OneHandSword;


public class Warrrior extends PersonBase {

    private final static String DEFAULT_NAME = "Warrior";
    private final static int DEFAULT_HP = 150;
    private final static int DEFAULT_MANA = 50;
    private final static int DEFAULT_ENERGY = 4;

    public Warrrior(String imie, int hp, int mana, int energy) {
        super(imie, hp, mana, energy);
//        assignSkills();
    }

    public Warrrior(String imie) {
        super(imie, DEFAULT_HP, DEFAULT_MANA, DEFAULT_ENERGY);
//        assignSkills();
    }

    public Warrrior() {
        super(DEFAULT_NAME, DEFAULT_HP, DEFAULT_MANA, DEFAULT_ENERGY);
//        assignSkills();
    }

    @Override
    public void assignSkills()
    {
        this.addSkill(new OneHandSword(2, 2, 0));
    }

    public Drawable getThemeImage(Resources resources)
    {
        Drawable drawable = resources.getDrawable( R.drawable.warrior, null);
        return drawable;
    }


    @Override
    public String toString() {
        return "Warrrior{" +
                "imie='" + imie + '\'' +
                ", hp=" + hp +
                ", mana=" + mana +
                ", energy=" + energy +
                '}';
    }
}
