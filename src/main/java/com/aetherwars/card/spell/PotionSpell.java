package com.aetherwars.card.spell;

import com.aetherwars.card.Card;
import com.aetherwars.card.Character.Character;
import com.aetherwars.card.SummonedCharacter;

public class PotionSpell extends Spell {
    private int attack;
    private int health;
    private int duration;

    public PotionSpell(int id, String name, int mana, String description, String imagepath, int attack, int health, int duration) {
        super(id, name, mana, description, imagepath);
        this.attack = attack;
        this.health = health;
        this.duration = duration;
        this.stat = (attack!=0?("ATK"+(attack>0?("+"+attack):attack)):"")+(attack!=0&&health!=0?"/":"")+(health!=0?("HP"+(health>0?("+"+health):health)):"")+"("+duration+")";
    }

    public int getAttack() {
        return this.attack;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void use(SummonedCharacter target) {
        /* PTN / Potion (TEMP):
        Meningkatkan atribut attack dan / atau health.
        Penambahan ini dapat bernilai 0 bahkan negatif.
        Penambahan health dilakukan secara bertumpuk, sehingga serangan musuh akan
        mengambil health dari efek potion terakhir terlebih dahulu sebelum health dari karakter
        atau efek potion sebelumnya. Pengurangan health dapat membunuh karakter.
        Pengurangan attack tidak akan membuat attack karakter kurang dari 0. */
        // TODO: kalau sudah nol, balik status menjadi normal
        // TODO: append lagi dapet efek potion swap ke target
        /* target.setHealth(
                this.getHealth() > 0
                ? Math.min(target.getHealth() + this.getHealth(), target.getMaxHealth())
                : Math.max(target.getHealth() + this.getHealth(), 0)
        );
        target.setAttack(
                this.getAttack() > 0
                ? Math.min(target.getAttack() + this.getAttack(), target.getMaxAttack())
                : Math.max(target.getAttack() + this.getAttack(), 0)
        ); */
    }

    @Override
    public Card cloneCard() {
        PotionSpell potion = new PotionSpell(id, name, mana, description, imagepath,attack,health,duration);
        return potion;
    }
}
