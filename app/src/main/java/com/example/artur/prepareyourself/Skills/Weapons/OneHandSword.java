package com.example.artur.prepareyourself.Skills.Weapons;

import android.content.Context;

import com.example.artur.prepareyourself.Skills.SkillBase;

public class OneHandSword extends SkillBase {

    private final static int DAMAGE_MULTIPLER = 1;

    protected static String name = "One Hand Sword";

    public OneHandSword(int damage, int energy, int mana) {

        super(name, damage * DAMAGE_MULTIPLER, energy, mana);
    }

    @Override
    public void action(Context appContext) {
//        return this.getDamage();
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
