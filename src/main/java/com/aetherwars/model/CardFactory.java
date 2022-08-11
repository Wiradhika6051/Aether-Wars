package com.aetherwars.model;

import java.util.HashMap;
import java.util.Map;


public class CardFactory {
    //factory untuk fetch kartu
    private Map<String,CardDatabase> cardDatabases;
    public CardFactory() {
        cardDatabases = new HashMap<>();
    }
    public void insertDatabase(CardDatabase cardDatabase, String type){
        cardDatabases.put(type,cardDatabase);
    }
    public CardDatabase getDatabase(String type){
        return cardDatabases.get(type);
    }
}
