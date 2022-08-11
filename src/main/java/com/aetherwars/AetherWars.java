package com.aetherwars;

import java.io.IOException;
import java.net.URISyntaxException;

import com.aetherwars.controller.BoardController;
import com.aetherwars.event.BoardChannel;
import com.aetherwars.model.CardFactory;
import com.aetherwars.model.Player;
import com.aetherwars.view.Frame;

public class AetherWars{
  public static void main(String[] args) {
    BoardChannel channel = new BoardChannel();
    BoardController board = new BoardController(channel);
    Player p1 = new Player("Steve");
    Player p2 = new Player("Alex");
    CardFactory cardFactory=null;

    try {
      cardFactory = GlobalInitializer.loadCards();
    }
    catch(IOException ioe){
      System.out.println("File tidak ditemukan!");
    }
    catch(URISyntaxException e){
      System.out.println("Sintaks URI bermasalah!");
    }
    catch(Exception e){
      System.out.println("Failed to load cards: " + e);
    }
    Game game = new Game(p1,p2,null,cardFactory);
    Frame main_frame = new Frame(false);
    launch();
  }
  static void launch(){

  }
}