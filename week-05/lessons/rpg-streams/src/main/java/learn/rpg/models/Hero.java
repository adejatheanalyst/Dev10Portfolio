package learn.rpg.models;

import learn.rpg.Nation;
import learn.rpg.Profession;

import java.math.BigDecimal;

public class Hero {

    private String name;
    private Nation nation;
    private Profession profession;
    private int power;
    private int wit;
    private int slipperiness;
    private int level;
    private BigDecimal coin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getWit() {
        return wit;
    }

    public void setWit(int wit) {
        this.wit = wit;
    }

    public int getSlipperiness() {
        return slipperiness;
    }

    public void setSlipperiness(int slipperiness) {
        this.slipperiness = slipperiness;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public BigDecimal getCoin() {
        return coin;
    }

    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", nation=" + nation +
                ", profession=" + profession +
                ", power=" + power +
                ", wit=" + wit +
                ", slipperiness=" + slipperiness +
                ", level=" + level +
                ", coin=" + coin +
                '}';
    }
}
