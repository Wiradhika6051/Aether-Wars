/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aetherwars.view;

import com.aetherwars.GUI.Components.*;
import com.aetherwars.GUI.Selectable;
import com.aetherwars.Game;
import com.aetherwars.card.Card;
import com.aetherwars.card.Character.Type;
import com.aetherwars.card.Deck;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.controller.BoardController;
import com.aetherwars.card.Character.Character;
import com.aetherwars.card.Character.Type;
import com.aetherwars.model.Phase;
import com.aetherwars.util.GlobalVar;
import com.aetherwars.util.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import static com.aetherwars.util.Utility.getFractionSize;
import static java.lang.Math.abs;

/**
 *
 * @author Fahmi
 */
public class Frame extends javax.swing.JFrame {
    private Dimension screenSize;
    private int screenWidth;
    private int screenHeight;
    public static Frame Instance;
    HandCardLabel[] deck;
    PlayerPicture[] players;
    ShadowyScreen drawPhaseScreen;
    HandCardLabel[] drawableCards;
    Game game;

    static final String REMOVE_CARD_PROMPT_MESSAGE = "Apakah Anda yakin membuang kartu ini?";
    static final String HANDCARD_FULL_MESSAGE = "<html><p>Kartu Di Tangan Sudah Penuh!</p><p>Buang Salah Satu dengan Klik Kanan pada Kartu!</p></html>";

    boolean isMasked;//apakah jumlah kartu di deck sudah disamarkan pas draw phase(smeestinya -3 tapis eolah olah gak berkurang)

    /**
     * Creates new form Frame
     */
    private boolean isDebugMode;//jika true, maka fitur debug aktif
    public Frame(boolean isDebugMode,Game game) {
      //  RemovePrompt rp = new RemovePrompt();
      //  add(rp);
      //  rp.setBounds(0,0,1000,1000);
        Frame.Instance = this;
        isMasked = false;
        this.game = game;
        setTitle("Aether Wars");
    //    turn = 1;
        screenWidth = GlobalVar.getScreenWidth();
        screenHeight = GlobalVar.getScreenHeight();
        this.isDebugMode = isDebugMode;
        System.out.println(isDebugMode);
        this.setSize(new Dimension((int)(this.screenWidth),(int)(this.screenHeight)));
        players = new PlayerPicture[2];
        this.setLayout(null);
        init();
        initPlayer();
        initKeyListener();
        initDeck();
        initdrawPhaseScreen();
        initRemovePrompt();
        setVisible(true);
    }
    void initRemovePrompt(){
        rp.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),0,60),
                getFractionSize(GlobalVar.getScreenHeight(),0,40),
                getFractionSize(GlobalVar.getScreenWidth(),60,60),
                getFractionSize(GlobalVar.getScreenHeight(),40,40)
        );
        maxCardWarning.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),22,60),
                getFractionSize(GlobalVar.getScreenHeight(),7,40),
                getFractionSize(GlobalVar.getScreenWidth(),19,60),
                getFractionSize(GlobalVar.getScreenHeight(),3,40)
        );
        maxCardWarning.setFont(new Font("Default",Font.BOLD,24));
        maxCardWarning.setForeground(Color.red);
    }
    void showMaxCardWarning(){
        add(maxCardWarning);
        for (Component c : this.getContentPane().getComponents()) {
            if (HandCardLabel.class.isInstance(c)) {
                Selectable comp = (Selectable) c;
                comp.setSelectability(true);
            }
        }
    }
    public void renderDrawScreen(boolean isEnd){
        if(isEnd){
            for (int i = 0; i < drawableCards.length; i++) {
                remove(drawableCards[i]);
            }
            remove(drawPhaseScreen);
        }
        else if(game.getPlayer(game.getCurPlayer()).getHandCardSize()==5){
            //hapus salah satu kartu
            //removeCardScreen();
            //add(rp);
            showMaxCardWarning();
        }
        else{
            for (int i = 0; i < drawableCards.length; i++) {
                add(drawableCards[i]);
            }
            add(drawPhaseScreen);
        }
    }
    public void drawPhase(boolean isEnd){
    //   removeAll();
        System.out.println("eheee123e3");
        renderDrawScreen(isEnd);
        if(isEnd){
            /*
            for (Component c : this.getContentPane().getComponents()) {
                if (Selectable.class.isInstance(c)) {
                    Selectable comp = (Selectable) c;
                    comp.setSelectability(true);
                }
            }
            */
            for (Component c : this.getContentPane().getComponents()) {
                if (NextButton.class.isInstance(c)) {
                    Selectable comp = (Selectable) c;
                    comp.setSelectability(true);
                }
            }
        }
        else {
            for (Component c : this.getContentPane().getComponents()) {
                if (Selectable.class.isInstance(c)) {
                    Selectable comp = (Selectable) c;
                    comp.setSelectability(false);
                }
            }
        }
        System.out.println("ejhfreifr");
        renderComponents(getDebugMode());
        revalidate();
        repaint();
    }
    void initdrawPhaseScreen(){
        drawPhaseScreen = new ShadowyScreen();
        drawPhaseScreen.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),0,60),
                getFractionSize(GlobalVar.getScreenHeight(),0,40),
                getFractionSize(GlobalVar.getScreenWidth(),60,60),
                getFractionSize(GlobalVar.getScreenHeight(),40,40)
        );
        drawableCards = new HandCardLabel[3];
        for(int i=0;i<drawableCards.length;i++){
            drawableCards[i] = new HandCardLabel(true);
            drawableCards[i].setBounds(
                    getFractionSize(GlobalVar.getScreenWidth(),10*(i+1)+5*i,60),
                    getFractionSize(GlobalVar.getScreenHeight(),10,40),
                    getFractionSize(GlobalVar.getScreenWidth(),10,60),
                    getFractionSize(GlobalVar.getScreenHeight(),18,40)
            );
            drawableCards[i].setCharacter(game.getDeck(game.getCurPlayer()).drawCard());
        }

    }

    void initPlayer(){
        players[0] = player1Picture;
        players[1] = player2Picture;
    }
    void initDeck(){
        deck = new HandCardLabel[5];
        deck[0]=handCard1;
        deck[1]=handCard2;
        deck[2]=handCard3;
        deck[3]=handCard4;
        deck[4]=handCard5;
    }
    public HandCardLabel[] getDeck(){
        return deck;
    }
    public static Frame getInstance(){
        return Instance;
    }
    public void afterDraw(){
        for(int i=0;i<drawableCards.length;i++) {
            if(drawableCards[i].getCard()!=null) {
                System.out.println("hpere");
                //acak posisi masuk kartu
                Random random = new Random();
                Deck deck = game.getDeck(game.getCurPlayer());
                int idx = abs(random.nextInt(deck.getSize()));
                System.out.println(idx);
                deck.insertCardAdd(drawableCards[i].getCard(),idx);
                System.out.println(deck.getSize());
            }
        }
    }
    public void setDebugMode(boolean debugMode){
        this.isDebugMode= debugMode;
        /*
        if(isDebugMode){
            pnl_card_preview.showSprite("/com/aetherwars/card/image/character/Sheep.png");
        }
        else{
            pnl_card_preview.showSprite(null);
        }*/
    }
    public void initPhase(){
        //update turn
        pnl_turns.updateTurn(game.getTurn());
        //update healthbar
        healthbar1.updateHP(game.getPlayer(0).getHP());
        healthbar2.updateHP(game.getPlayer(1).getHP());
        //update deck
        cardDeckPanel.setMaxCard(game.getDeck(game.getCurPlayer()).getMaxSize());
        cardDeckPanel.updateDeck(game.getDeck(game.getCurPlayer()).getSize());
        //update mana label
        showManaLabel.updateMaxMana(game.getPlayer(game.getCurPlayer()).getMaxMana());
        showManaLabel.updateCurrentMana(game.getPlayer(game.getCurPlayer()).getMana());
        //isi kartu
        Card[] handCard = game.getPlayer(game.getCurPlayer()).getHandCard();
        for(int i =0;i<handCard.length;i++){
            deck[i].setCharacter(handCard[i]);
        }
        //render kartu
        resetDeckCard();
        //reset highlight
        for(PlayerPicture pp:players){
            pp.dehightlight();
        }
        players[game.getCurPlayer()].highlight();
    }
    void resetDeckCard(){
        //reset semua kartu
        for(HandCardLabel card:deck){
            remove(card);
        }
        //isi ulang
        int i=0;
        for(HandCardLabel card:deck){
            if(card.isRendered()){
                add(card);
                System.out.println(i);
                i++;
            }
        }
        revalidate();
        repaint();
    }
    public ProgressPanel getStateGUI(String phase){
        switch(phase){
            case "DRAW":
                return pnl_draw_phase;
            case "PLAN":
                return pnl_plan_phase;
            case "ATTACK":
                return pnl_attack_phase;
            case "END":
                return pnl_end_phase;
            default:
                return null;
        }
    }
    public boolean getDebugMode(){
        return isDebugMode;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
  //  @SuppressWarnings("unchecked")
   // // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void init() {
        rp = new RemovePrompt(REMOVE_CARD_PROMPT_MESSAGE);
        maxCardWarning = new JLabel(HANDCARD_FULL_MESSAGE);
        pnl_turns = new TurnInfo();
        player1Picture = new PlayerPicture("/com/aetherwars/card/image/Player/Steve.png");
        deck_A_player1 = new PlayerDeckCard("A");
        deck_C_player1 = new PlayerDeckCard("C");
 //       deck_C_player1.addCharacter(new SummonedCharacter(new Character(1,"Enderman",100,"Penghuni The End","/com/aetherwars/card/image/character/Enderman.png", com.aetherwars.card.Character.Type.END,10,2,12,2),2));
        deck_B_player1 = new PlayerDeckCard("B");
        //     deck_B_player1.addCharacter(new SummonedCharacter(new Character(1,"Enderman",100,"Penghuni The End","/com/aetherwars/card/image/character/Enderman.png", com.aetherwars.card.Character.Type.END,8,2,10,2),2));
  //      deck_B_player1.setSelected(true);
        deck_D_player1 = new PlayerDeckCard("D");
        deck_E_player1 = new PlayerDeckCard("E");
        player2Picture = new PlayerPicture("/com/aetherwars/card/image/Player/Alex.jpg");
        deck_A_player2 = new PlayerDeckCard("A");
        deck_B_player2 = new PlayerDeckCard("B");
        deck_D_player2 = new PlayerDeckCard("D");
        deck_E_player2 = new PlayerDeckCard("E");
        deck_C_player2 = new PlayerDeckCard("C");
        pnl_draw_phase = new ProgressPanel("DRAW");
        pnl_draw_phase.setSelected(true);
        pnl_plan_phase = new ProgressPanel("PLAN");
        pnl_attack_phase = new ProgressPanel("ATTACK");
        pnl_end_phase = new ProgressPanel("END");
        nextPhaseButton = new NextButton();
        handCard1 = new HandCardLabel(false);
        handCard2 = new HandCardLabel(false);
        handCard3 = new HandCardLabel(false);
        handCard4 = new HandCardLabel(false);
        handCard5 = new HandCardLabel(false);
        pnl_card_preview = new CardPreviewPanel();
     //   pnl_card_description = new CardDescriptionPanel(new SummonedCharacter(new Character(1,"Enderman",100,"Penghuni The End","/com/aetherwars/card/image/character/Enderman.png", com.aetherwars.card.Character.Type.END,10,2,12,2),2));
        pnl_card_description = new CardDescriptionPanel();
        cardDeckPanel = new CardDeckPanel();
        showManaLabel = new ShowManaLabel(0,1);
        descriptionPanel = new DescriptionPanel();
        //new layout
        //note: rendernya kek pake stack, jadi urutannya kebalik, yg terakhir ditambahin yang
        //pertama di render
        //grid debug
        grid = new GridHelper();
        if(isDebugMode) {
            this.add(grid);
        }
        //healthbar player 1
        healthbar1 = new HealthBar(80,80,Component.LEFT_ALIGNMENT);
        healthbar1.setBounds((int) ((1 / 60.0) * GlobalVar.getScreenWidth()),
               (int) (2 / 40.0 * GlobalVar.getScreenHeight()),
                (int)(15 / 60.0 * GlobalVar.getScreenWidth()),
                (int) (1.5 / 40.0 * GlobalVar.getScreenHeight()));
        this.add(healthbar1);
        //healthbar player 2
        healthbar2 = new HealthBar(80,80,Component.RIGHT_ALIGNMENT);
        healthbar2.setBounds((int) ((43 / 60.0) * GlobalVar.getScreenWidth()),
                (int) (2 / 40.0 * GlobalVar.getScreenHeight()),
                (int)(15 / 60.0 * GlobalVar.getScreenWidth()),
                (int) (1.5 / 40.0 * GlobalVar.getScreenHeight()));
        this.add(healthbar2);
        //player 1 name
        player1Name = new JLabel("Steve");
        player1Name.setFont(new Font("Serif",Font.BOLD,32));
        player1Name.setBounds(getFractionSize(GlobalVar.getScreenWidth(),1.5,60),
                getFractionSize(GlobalVar.getScreenHeight(),2.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),10,60),
                getFractionSize(GlobalVar.getScreenHeight(),5,40));
        add(player1Name);
        //player 2 name
        player2Name = new JLabel("Alex");
        player2Name.setFont(new Font("Serif",Font.BOLD,32));
        player2Name.setBounds(getFractionSize(GlobalVar.getScreenWidth(),55,60),
                getFractionSize(GlobalVar.getScreenHeight(),2.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),10,60),
                getFractionSize(GlobalVar.getScreenHeight(),5,40));
        add(player2Name);
        //turn info
        pnl_turns.setBounds(getFractionSize(GlobalVar.getScreenWidth(),28,60),
                getFractionSize(GlobalVar.getScreenHeight(),1,60),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60));
        add(pnl_turns);
        //foto player 1
        System.out.println("ehe");
        player1Picture.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),3,60),
                getFractionSize(GlobalVar.getScreenHeight(),11,40),
                getFractionSize(GlobalVar.getScreenWidth(),4,60),
                getFractionSize(GlobalVar.getScreenHeight(),4,40));
        add(player1Picture);
        //foto player 2
        player2Picture.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),53,60),
                getFractionSize(GlobalVar.getScreenHeight(),11,40),
                getFractionSize(GlobalVar.getScreenWidth(),4,60),
                getFractionSize(GlobalVar.getScreenHeight(),4,40));
        add(player2Picture);
        //deck player A
        //deck A
        deck_A_player1.setBounds(
               getFractionSize(GlobalVar.getScreenWidth(),8,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),8.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_A_player1);
        //deck B
        deck_B_player1.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),13,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),8.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_B_player1);
        //deck C
        deck_C_player1.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),8,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),13.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_C_player1);
        //deck D
        deck_D_player1.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),13,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),13.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_D_player1);
        //deck E
        deck_E_player1.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),18,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),11.1,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_E_player1);
        //deck player B
        //deck A
        deck_A_player2.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),47,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),8.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_A_player2);
        //deck B
        deck_B_player2.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),42,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),8.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_B_player2);
        //deck C
        deck_C_player2.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),47,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),13.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_C_player2);
        //deck D
        deck_D_player2.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),42,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),13.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_D_player2);
        //deck E
        deck_E_player2.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),37,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),11.1,40),
                getFractionSize(GlobalVar.getScreenWidth(),4.5,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),4.5,40)
        );
        add(deck_E_player2);
        //phase
        //draw
        pnl_draw_phase.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),1,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),21,40),
                getFractionSize(GlobalVar.getScreenWidth(),13,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),1.25,40)
        );
        add(pnl_draw_phase);
        //plan
        pnl_plan_phase.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),14.03,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),21,40),
                getFractionSize(GlobalVar.getScreenWidth(),13,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),1.25,40)
        );
        add(pnl_plan_phase);
        //attack
        pnl_attack_phase.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),27.03,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),21,40),
                getFractionSize(GlobalVar.getScreenWidth(),13,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),1.25,40)
        );
        add(pnl_attack_phase);
        //end
        pnl_end_phase.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),40.03,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),21,40),
                getFractionSize(GlobalVar.getScreenWidth(),13,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),1.25,40)
        );
        add(pnl_end_phase);
        //next phase button
        nextPhaseButton.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),54,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),21,40),
                getFractionSize(GlobalVar.getScreenWidth(),4,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),1.25,40)
        );
        add(nextPhaseButton);
        //kartu di tangan
        //slot 1
        handCard1.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 1, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 24, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 9, 40)
        );
        //slot 2
        handCard2.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 6.0313, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 24, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 9, 40)
        );
        //slot 3
        handCard3.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 11.07, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 24, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 9, 40)
        );
        //slot4
        handCard4.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 16.1, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 24, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 9, 40)
        );
        //slot5
        handCard5.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 21.14, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 24, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 9, 40)
        );
        //preview kartu
        pnl_card_preview.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 29, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 23, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 6, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 11, 40)
        );
        add(pnl_card_preview);
        //stat kartu
        pnl_card_description.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 36, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 24, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 8.5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 9, 40)
        );
        add(pnl_card_description);
        //deskripsi kartu
        descriptionPanel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 44.5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 24, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 8.5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 9, 40)
        );
        add(descriptionPanel);
        //info jumlah kartu di deck
        cardDeckPanel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 54, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 25, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 3, 40)
        );
        add(cardDeckPanel);
        //info jumlah mana player
        showManaLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 54, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 29, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 3, 40)
        );
        add(showManaLabel);

     //   for(Component c:this.getContentPane().getComponents()){
     //       System.out.println(c);
     //   }
    }

    private void initKeyListener(){
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {//kirim ke user input
                //  System.out.println("printed key:"+e.getKeyCode());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("pressed key:"+e.getKeyCode());
                switch(e.getKeyCode()){
                    case 112://F1 ->aktifkan/nonaktifkan debug mode
                   //     pnl_turns.updateTurn(++turn);
                        Frame instance = Frame.getInstance();
                        boolean debugMode = instance.getDebugMode();
                        instance.setDebugMode(!debugMode);
                    //    cardNum = (cardNum==5)? 1:(cardNum+1);
                       // CURCARD++;
                       // CURCARD%=61;
                       // cardDeckPanel.updateDeck(CURCARD);
                      //  showManaLabel.updateCurrentMana(3);
                      //  showManaLabel.updateMaxMana(4);
                        instance.getContentPane().removeAll();
                        instance.renderComponents(instance.getDebugMode());
                        instance.revalidate();
                        instance.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public void reset(boolean debug){
        //isi kartu
        Card[] handCard = game.getPlayer(game.getCurPlayer()).getHandCard();
        for(int i =0;i<deck.length;i++){
            if(i<handCard.length) {
                deck[i].setCharacter(handCard[i]);
            }
            else{
                deck[i].removeCard();
            }
        }
    }
    public void clickHandDeck(int idx){
        deck[idx].setEnabled(true);
        deck[idx].setSelectability(false);
    }
    public void renderComponents(boolean debugMode){
        if(debugMode){
            this.add(grid);
        }
        if(Phase.DRAW==game.getCurPhase()){
            if(!game.getDrawPhaseEndFlag()&&!isMasked) {
                cardDeckPanel.updateDeck(cardDeckPanel.getCard() + 3);
                isMasked = true;
            }
            else if(game.getDrawPhaseEndFlag()){
                System.out.println("ehe"+game.getDeck(game.getCurPlayer()).getSize());
                cardDeckPanel.updateDeck(game.getDeck(game.getCurPlayer()).getSize());
            }
            else if(isMasked){
                cardDeckPanel.updateDeck(cardDeckPanel.getCard());
            }
            renderDrawScreen(game.getDrawPhaseEndFlag());
        }
        add(showManaLabel);
        add(cardDeckPanel);
        add(descriptionPanel);
        add(pnl_card_description);
        resetDeckCard();
        System.out.println("eheeee");
        add(pnl_card_preview);
        add(nextPhaseButton);
        add(pnl_end_phase);
        add(pnl_attack_phase);
        add(pnl_plan_phase);
        add(pnl_draw_phase);
        add(deck_E_player2);
        add(deck_D_player2);
        add(deck_C_player2);
        add(deck_B_player2);
        add(deck_A_player2);
        add(deck_E_player1);
        add(deck_D_player1);
        add(deck_C_player1);
        add(deck_B_player1);
        add(deck_A_player1);
        add(player2Picture);
        add(player1Picture);
        add(pnl_turns);
        add(player2Name);
        add(player1Name);
        add(healthbar2);
        add(healthbar1);
    }
    // </editor-fold>//GEN-END:initComponents
    public void run() {
        this.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frame.getInstance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private DescriptionPanel descriptionPanel;
    private PlayerPicture player1Picture;
    private PlayerDeckCard deck_A_player1;
    private PlayerDeckCard deck_C_player1;
    private PlayerDeckCard deck_B_player1;
    private PlayerDeckCard deck_D_player1;
    private PlayerDeckCard deck_E_player1;
    private PlayerPicture player2Picture;
    private CardDeckPanel cardDeckPanel;
    private PlayerDeckCard deck_A_player2;
    private PlayerDeckCard deck_B_player2;
    private PlayerDeckCard deck_D_player2;
    private PlayerDeckCard deck_E_player2;
    private PlayerDeckCard deck_C_player2;
    private HandCardLabel handCard3;
    private HandCardLabel handCard4;
    private HandCardLabel handCard5;
    private ShowManaLabel showManaLabel;
    private ProgressPanel pnl_attack_phase;
    private CardDescriptionPanel pnl_card_description;
    private CardPreviewPanel pnl_card_preview;
    private ProgressPanel pnl_draw_phase;
    private ProgressPanel pnl_end_phase;
    private HandCardLabel handCard1;
    private HandCardLabel handCard2;
    private ProgressPanel pnl_plan_phase;
    private TurnInfo pnl_turns;
    private HealthBar healthbar1;
    private HealthBar healthbar2;
    private JLabel player1Name;
    private JLabel player2Name;
    private NextButton nextPhaseButton;
    private GridHelper grid;
    RemovePrompt rp;
    JLabel maxCardWarning;
   // private CardLabel card;
    // End of variables declaration//GEN-END:variables
}