package com.aetherwars.util.initializer;

import com.aetherwars.card.Card;
import com.aetherwars.model.CardDatabase;

import java.util.List;

public interface Initializer<T extends Card> {
    public CardDatabase loadCard();
}
