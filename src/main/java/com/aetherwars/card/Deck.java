package com.aetherwars.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    int MAX_SIZE;

    public Deck(int MaxCard) {
        this.cards = new ArrayList<>();
        MAX_SIZE = MaxCard;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
    public void insertCardAdd(Card card,int idx){
        cards.add(idx,card);
    }


    public void removeCard(Card card) {
        int idx = cards.indexOf(card);
        if(idx!=-1) {
            cards.remove(idx);
        }
        System.out.println(cards.size());
    }

    public Card drawCard() {
        int idx = cards.size()-1;
        Card card = cards.get(idx);
     //   System.out.println("ngedraw");
        removeCard(card);
        return card;
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
    public int getMaxSize(){
        return MAX_SIZE;
    }
    public int getSize(){
        return cards.size();
    }
}
