package com.aetherwars.model;

import com.aetherwars.card.Card;
import com.aetherwars.card.Character.Character;

import java.util.HashMap;
import java.util.Map;


public class CardDatabase {
    //penerapan design pattern flyweight buat nyimpen data tentang kartu
    Map<Integer,Card> charaDatabases;
    public CardDatabase() {
        charaDatabases = new HashMap<>();
     }

    public Card getCard(int id) {
        return charaDatabases.get(id);
    }

    public void insertCard(Card card) {
        charaDatabases.put(card.getId(),card);
    }
}
