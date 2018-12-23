package com.example.artur.prepareyourself.Persons;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.artur.prepareyourself.R;
import com.example.artur.prepareyourself.Skills.SkillBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class PersonBase implements Serializable {

    protected int ENERGY_RECOVER = 2;
    protected int MANA_RECOVER = 0;
    protected int HP_RECOVER = 0;

    protected String imie;

    protected int hp;
    protected int maxHp;


    protected int mana;
    protected int maxMana;

    protected int energy;
    protected int maxEnergy;

    private List<SkillBase> skills = new ArrayList<SkillBase>();

    public void addSkill(SkillBase skill)
    {
        skills.add(skill);
    }

    public PersonBase(String imie, int hp, int mana, int energy) {
        this.imie = imie;
        this.hp   = this.maxHp = hp;
        this.mana = this.maxMana = mana;
        this.energy = this.maxEnergy =  energy;

        this.assignSkills();
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void addEnergy(int energy)
    {
        this.energy += energy;
    }

    public void addHp(int hp)
    {
        this.hp += hp;
    }

    public void addMana(int mana)
    {
        this.mana += mana;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public List<SkillBase> getSkills() {
        return skills;
    }

    public void assignSkills(){}

    public void nextTurn()
    {
        this.addEnergy(this.ENERGY_RECOVER);
        this.addHp(this.HP_RECOVER);
        this.addMana(this.MANA_RECOVER);
    }

    public void setSkills(List<SkillBase> skills) {
        this.skills = skills;
    }

    public Drawable getThemeImage(Resources resources)
    {
        Drawable drawable = resources.getDrawable( R.drawable.main, null);
        return drawable;
    }


    @Override
    public String toString() {
        return "PersonBase{" +
                "imie='" + imie + '\'' +
                ", hp=" + hp +
                ", mana=" + mana +
                ", energy=" + energy +
                ", skills=" + skills +
                '}';
    }
}
