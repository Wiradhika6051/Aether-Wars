package com.aetherwars.util.initializer;

import com.aetherwars.AetherWars;
import com.aetherwars.card.Character.Character;
import com.aetherwars.card.Character.Type;
import com.aetherwars.model.CardDatabase;
import com.aetherwars.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CharacterInitializer implements Initializer<Character>{
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    @Override
    public CardDatabase loadCard() {
        CardDatabase listChar = new CardDatabase();
        try {
            File characterCSVFile = new File(AetherWars.class.getResource(CHARACTER_CSV_FILE_PATH).toURI());
            CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
            characterReader.setSkipHeader(true);
            List<String[]> characterRows = characterReader.read();
            for(String[] row:characterRows){
                Character c = new Character(
                        Integer.parseInt(row[0]),
                        row[1],
                        Integer.parseInt(row[7]),
                        row[3],
                        row[4],
                        Type.valueOf(row[2]),
                        Integer.parseInt(row[5]),
                        Integer.parseInt(row[8]),
                        Float.parseFloat(row[6]),
                        Integer.parseInt(row[9])
                );
                System.out.println(c);
                listChar.insertCard(c);
            }
        }
        catch(URISyntaxException e){
            System.out.println("Synthax URI salah");
        }
        catch(IOException e){
            System.out.println("Terjadi kesalahan saat pembacaan data");
        }
        return listChar;
    }
}
