package com.aetherwars.card;

public class Card {
    private int id;
    private String name;
    private int mana;
    private String description;
    private int imagepath;

    public Card(int id, String name, int mana, String description, int imagepath) {
        this.id = id;
        this.name = name;
        this.mana = mana;
        this.description = description;
        this.imagepath = imagepath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImagepath() {
        return imagepath;
    }

    public void setImagepath(int imagepath) {
        this.imagepath = imagepath;
    }
}
