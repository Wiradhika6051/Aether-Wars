package com.aetherwars.card;

public abstract class Card{
    protected int id;
    protected String name;
    protected int mana;
    protected String description;
    protected String imagepath;
    protected String stat;

    public Card(int id, String name, int mana, String description, String imagepath) {
        this.id = id;
        this.name = name;
        this.mana = mana;
        this.description = description;
        this.imagepath = imagepath;
    }
    public Card(Card card){

    }
    public abstract Card cloneCard();
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

    public String getImagepath() {
        return "/com/aetherwars/"+imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getStat(){
        return stat;
    }

}
