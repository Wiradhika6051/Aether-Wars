package com.aetherwars.util.initializer;

import com.aetherwars.AetherWars;
import com.aetherwars.card.spell.PotionSpell;
import com.aetherwars.card.spell.SwapSpell;
import com.aetherwars.model.CardDatabase;
import com.aetherwars.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class SwapSpellInitializer implements Initializer<SwapSpell>{
    private static final String SPELL_SWAP_CSV_FILE_PATH = "card/data/spell_swap.csv";
    @Override
    public CardDatabase loadCard() {
        CardDatabase listSwapSpell = new CardDatabase();
        try {
            File potionSwapCSVFile = new File(AetherWars.class.getResource(SPELL_SWAP_CSV_FILE_PATH).toURI());
            CSVReader potionSwapReader = new CSVReader(potionSwapCSVFile, "\t");
            potionSwapReader.setSkipHeader(true);
            List<String[]> potionSwapRows = potionSwapReader.read();
            for(String[] row:potionSwapRows){
                SwapSpell sp = new SwapSpell(
                        Integer.parseInt(row[0]),
                        row[1],
                        Integer.parseInt(row[5]),
                        row[2],
                        row[3],
                        Integer.parseInt(row[4])
                );
                System.out.println(sp);
                listSwapSpell.insertCard(sp);
            }
        }
        catch(URISyntaxException e){
            System.out.println("Synthax URI salah");
        }
        catch(IOException e){
            System.out.println("Terjadi kesalahan saat pembacaan data");
        }
        return listSwapSpell;
    }
}
