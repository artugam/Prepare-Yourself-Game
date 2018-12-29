package com.example.artur.prepareyourself.Persons.PlayerClasses;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.artur.prepareyourself.Persons.PersonBase;
import com.example.artur.prepareyourself.R;
import com.example.artur.prepareyourself.Skills.Spells.JumpAttack;
import com.example.artur.prepareyourself.Skills.Weapons.OneHandSword;


public class Warrior extends PersonBase {

    private final static String DEFAULT_NAME = "Warrior";
    private final static int DEFAULT_HP = 150;
    private final static int DEFAULT_ENERGY = 4;

    public Warrior(String imie, int hp, int energy) {
        super(imie, hp, energy);
    }

    public Warrior(String imie) {
        super(imie, DEFAULT_HP, DEFAULT_ENERGY);
    }

    public Warrior() {
        super(DEFAULT_NAME, DEFAULT_HP, DEFAULT_ENERGY);
    }

    @Override
    public void assignSkills()
    {
        this.addSkill(new OneHandSword(2, 2));
        this.addSkill(new JumpAttack(15, 3));
    }

    public Drawable getThemeImage(Resources resources)
    {
        Drawable drawable = resources.getDrawable( R.drawable.warrior, null);
        return drawable;
    }


    @Override
    public String toString() {
        return "Warrior{" +
                "imie='" + imie + '\'' +
                ", hp=" + hp +
                ", energy=" + energy +
                '}';
    }
}
