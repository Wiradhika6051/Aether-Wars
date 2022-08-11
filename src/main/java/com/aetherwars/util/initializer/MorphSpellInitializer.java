package com.aetherwars.util.initializer;

import com.aetherwars.AetherWars;
import com.aetherwars.card.spell.MorphSpell;
import com.aetherwars.model.CardDatabase;
import com.aetherwars.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MorphSpellInitializer implements Initializer<MorphSpell> {
    private static final String SPELL_MORPH_CSV_FILE_PATH = "card/data/spell_morph.csv";
    @Override
    public CardDatabase loadCard() {
        CardDatabase listMorphSpell = new CardDatabase();
        try {
            File morphSpellCSVFile = new File(AetherWars.class.getResource(SPELL_MORPH_CSV_FILE_PATH).toURI());
            CSVReader morphSpellReader = new CSVReader(morphSpellCSVFile, "\t");
            morphSpellReader.setSkipHeader(true);
            List<String[]> morphSpellRows = morphSpellReader.read();
            for(String[] row:morphSpellRows){
                MorphSpell ms = new MorphSpell(
                        Integer.parseInt(row[0]),
                        row[1],
                        Integer.parseInt(row[5]),
                        row[2],
                        row[3],
                        Integer.parseInt(row[4])
                );
                System.out.println(ms);
                listMorphSpell.insertCard(ms);
            }
        }
        catch(URISyntaxException e){
            System.out.println("Synthax URI salah");
        }
        catch(IOException e){
            System.out.println("Terjadi kesalahan saat pembacaan data");
        }
        return listMorphSpell;
    }
}
