package com.aetherwars.model;

import com.aetherwars.card.Card;
import com.aetherwars.card.Character.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CardDatabase {
    //penerapan design pattern flyweight buat nyimpen data tentang kartu
    Map<Integer,Card> charaDatabases;
    ArrayList<Integer> keySetList=null;

    public CardDatabase() {
        charaDatabases = new HashMap<>();
     }

    public Card getCard(int id) {
        Card temp = charaDatabases.get(id);
        if(temp==null){
            System.out.println("anying "+id+" "+temp);
        }
       Card clone =  temp.cloneCard();
       return clone;
     //   return temp;
    }

    public void insertCard(Card card) {
        charaDatabases.put(card.getId(),card);
    }
    public int getSize(){
        return charaDatabases.size();
    }
    public List<Integer> getKeySet(){
        if(keySetList==null){
            keySetList = new ArrayList<Integer>(charaDatabases.keySet());
        }
        return keySetList;
    }
}
