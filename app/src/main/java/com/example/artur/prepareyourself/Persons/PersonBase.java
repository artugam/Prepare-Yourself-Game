package com.example.artur.prepareyourself.Persons;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.artur.prepareyourself.R;
import com.example.artur.prepareyourself.Skills.SkillBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class PersonBase implements Serializable {

    protected String imie;
    protected int hp;
    protected int mana;
    protected int energy;

    private List<SkillBase> skills = new ArrayList<SkillBase>();


    public void addSkill(SkillBase skill)
    {
        Log.d("addSKill", skill.toString());
        skills.add(skill);

    }

    public PersonBase(String imie, int hp, int mana, int energy) {
        this.imie = imie;
        this.hp = hp;
        this.mana = mana;
        this.energy = energy;

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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public List<SkillBase> getSkills() {
        return skills;
    }

    public void assignSkills()
    {

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
