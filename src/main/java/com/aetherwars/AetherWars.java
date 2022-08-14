package com.aetherwars;

import java.io.IOException;
import java.net.URISyntaxException;

import com.aetherwars.controller.BoardController;
import com.aetherwars.event.BoardChannel;
import com.aetherwars.model.CardFactory;
import com.aetherwars.model.Player;
import com.aetherwars.view.Frame;

public class AetherWars{
  BoardController board;
  BoardChannel channel=null;
  Player p1;
  Player p2;
  CardFactory cardFactory;
  Game game;
  Frame main_frame;

  public AetherWars() {
    p1 = new Player("Steve");
    p2 = new Player("Alex");
    cardFactory=null;

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
    game = new Game(p1,p2,null,cardFactory);
    main_frame = new Frame(false,game);
    channel = new BoardChannel(main_frame);
    board = new BoardController(channel,main_frame);
  }
  void launch(){
    main_frame.setVisible(true);
    System.out.println("egeee");
    game.setup();
  }
}