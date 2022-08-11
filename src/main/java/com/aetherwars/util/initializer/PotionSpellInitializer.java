package com.aetherwars.util.initializer;

import com.aetherwars.AetherWars;
import com.aetherwars.card.spell.MorphSpell;
import com.aetherwars.card.spell.PotionSpell;
import com.aetherwars.model.CardDatabase;
import com.aetherwars.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class PotionSpellInitializer implements Initializer<PotionSpell>{
    private static final String SPELL_POTION_CSV_FILE_PATH = "card/data/spell_ptn.csv";
    @Override
    public CardDatabase loadCard() {
        CardDatabase listPotionSpell = new CardDatabase();
        try {
            File potionSpellCSVFile = new File(AetherWars.class.getResource(SPELL_POTION_CSV_FILE_PATH).toURI());
            CSVReader potionSpellReader = new CSVReader(potionSpellCSVFile, "\t");
            potionSpellReader.setSkipHeader(true);
            List<String[]> potionSpellRows = potionSpellReader.read();
            for(String[] row:potionSpellRows){
                PotionSpell ps = new PotionSpell(
                        Integer.parseInt(row[0]),
                        row[1],
                        Integer.parseInt(row[6]),
                        row[2],
                        row[3],
                        Integer.parseInt(row[4]),
                        Integer.parseInt(row[5]),
                        Integer.parseInt(row[7])
                );
                System.out.println(ps);
                listPotionSpell.insertCard(ps);
            }
        }
        catch(URISyntaxException e){
            System.out.println("Synthax URI salah");
        }
        catch(IOException e){
            System.out.println("Terjadi kesalahan saat pembacaan data");
        }
        return listPotionSpell;
    }
}
