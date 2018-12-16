package com.example.artur.prepareyourself.Skills;

import java.io.Serializable;

public abstract class SkillBase implements SkillInterface, Serializable {

    protected int damage;
    protected int energy;
    protected int mana;
    protected String name = "No name";

    public SkillBase(String name, int damage, int energy, int mana) {
        this.name = name;
        this.damage = damage;
        this.energy = energy;
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getEffectImage()
    {
        return 0;
    }

    @Override
    public String toString() {
        return "SkillBase{" +
                "damage=" + damage +
                ", energy=" + energy +
                ", name='" + name + '\'' +
                '}';
    }
}
