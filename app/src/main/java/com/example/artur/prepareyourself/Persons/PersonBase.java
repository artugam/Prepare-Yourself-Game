package com.example.artur.prepareyourself.Persons;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.artur.prepareyourself.R;
import com.example.artur.prepareyourself.Skills.SkillBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class PersonBase implements Serializable {

    protected int ENERGY_RECOVER = 4;
    protected int HP_RECOVER = 0;

    protected String imie;
    protected int hp;
    protected int maxHp;

    protected boolean alive = true;

    protected int energy;
    protected int maxEnergy = 6;

    private List<SkillBase> skills = new ArrayList<SkillBase>();

    public void addSkill(SkillBase skill)
    {
        skills.add(skill);
    }

    public PersonBase(String imie, int hp, int energy) {
        this.imie = imie;
        this.hp   = this.maxHp = hp;
        this.energy = energy;

        this.assignSkills();
    }

    public int getENERGY_RECOVER() {
        return ENERGY_RECOVER;
    }

    public void setENERGY_RECOVER(int ENERGY_RECOVER) {
        this.ENERGY_RECOVER = ENERGY_RECOVER;
    }

    public int getHP_RECOVER() {
        return HP_RECOVER;
    }

    public void setHP_RECOVER(int HP_RECOVER) {
        this.HP_RECOVER = HP_RECOVER;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
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

        if(hp < 1)
        {
            this.alive = false;
            this.hp = 0;
            return;
        }
        this.hp = hp;
    }

    public void addEnergy(int energy)
    {
        if(this.energy + energy > this.getMaxEnergy())
        {
            this.energy = this.getMaxEnergy();
            return;
        }
        this.energy += energy;
    }

    public void addHp(int hp)
    {
        if(this.hp + hp > this.getMaxHp())
        {
            this.hp = this.getMaxHp();
            return;
        }
        this.hp += hp;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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
                ", energy=" + energy +
                ", skills=" + skills +
                '}';
    }
}
