package com.aetherwars;

import com.aetherwars.card.Character.Character;
import com.aetherwars.card.Character.Type;
import com.aetherwars.model.CardFactory;
import com.aetherwars.util.CSVReader;
import com.aetherwars.util.initializer.CharacterInitializer;
import com.aetherwars.util.initializer.MorphSpellInitializer;
import com.aetherwars.util.initializer.PotionSpellInitializer;
import com.aetherwars.util.initializer.SwapSpellInitializer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GlobalInitializer {
    //inisialisasi permainan sebelum dimulai
    public static CardFactory loadCards() throws IOException, URISyntaxException {
        CardFactory cf = new CardFactory();
        populateDatabase(cf);
        return cf;
    }
    private static void populateDatabase(CardFactory cf){
        //note: buat modding, kalau mau ditambahin , extends kelas ini terus override fungsi ini
        //dengan awalnya memanggil super.populateDatabase()
        //Card Database
        CharacterInitializer ci = new CharacterInitializer();
        cf.insertDatabase(ci.loadCard(),"Character");
        //Morph Spell Database
        MorphSpellInitializer msi = new MorphSpellInitializer();
        cf.insertDatabase(msi.loadCard(),"Morph Spell");
        //Potion Spell Database
        PotionSpellInitializer psi = new PotionSpellInitializer();
        cf.insertDatabase(psi.loadCard(),"Potion Spell");
        //Swap Spell Database
        SwapSpellInitializer ssi = new SwapSpellInitializer();
        cf.insertDatabase(ssi.loadCard(),"Swap Spell");
    }
}
