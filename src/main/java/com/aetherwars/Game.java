package com.aetherwars;
import com.aetherwars.model.*;
import com.aetherwars.card.*;
import com.aetherwars.card.Character.*;

import java.lang.Character;
import java.util.*;

import com.aetherwars.event.*;
import com.aetherwars.view.Frame;

public class Game implements Publisher, Subscriber{
    private final int MAX_CARD = 60;
    private final int MIN_CARD = 40;
    //pembagian kartu: 40% chara, 10% morph,35% potion,15% swap
    private final float CHARA_PERCENTAGE = 0.4f;
    private final float MORPH_PERCENTAGE = 0.1f;
    private final float POTION_PERCENTAGE = 0.35f;
    private final float SWAP_PERCENTAGE = 0.15f;

    private final int INITIAL_CARD = 5;


    private Player players[];
    private int turns;
    private int phase_id = 0;
    private Phase[] phases = new Phase[] {Phase.DRAW, Phase.PLAN, Phase.ATTACK, Phase.END};
    private int cur_player;
    private EventChannel channel;
    private Deck deck[];

    private static Game INSTANCE;

    Random random;
    CardFactory cf;
    boolean isDrawPhaseEnd;//bernilai true jika udah milih kartu
    
    public Game(Player p1, Player p2, EventChannel channel, CardFactory cf){
        turns = 1;
        this.cf = cf;
    	this.deck = new Deck[2];
        this.random = new Random(System.currentTimeMillis());
        //dapetin jumlah kartu yg dimasukkan (40-60 inclusive)
        int randd = Math.abs(random.nextInt());
        int maxCard =  MIN_CARD + randd%(MAX_CARD-MIN_CARD+1);
     //   System.out.println(maxCard+" "+((MAX_CARD-MIN_CARD+1))+" "+randd);
    	for(int i=0; i<2; i++) {
            this.deck[i]= new Deck(maxCard);
            populateDeck(this.deck[i],maxCard);
    	}
        this.players = new Player[2];
        this.players[0] = p1;
        this.players[1] = p2;
        this.channel = channel;
        this.cur_player = 0;
        INSTANCE = this;
    }
    public Phase getCurPhase(){
        System.out.println(phase_id+"aaaa");
        return phases[phase_id];
    }

    public static Game getGameManager(){
        return INSTANCE;
    }
    void populateDeck(Deck deck,int maxCard){
        //isi dengan chara
        int charCount = (int) (maxCard * CHARA_PERCENTAGE);
        CardDatabase charaDB = cf.getDatabase("Character");
        System.out.println("chara");
        for(int i =0;i<charCount;i++){
            int id = charaDB.getKeySet().get(random.nextInt(charaDB.getKeySet().size()));
            deck.addCard(charaDB.getCard(id));
        }
        //isi dengan spell morph
        int morphCount = (int) (maxCard * MORPH_PERCENTAGE);
        CardDatabase morphDB = cf.getDatabase("Morph Spell");
        System.out.println("morph");
        for(int i =0;i<morphCount;i++){
            int id = morphDB.getKeySet().get(random.nextInt(morphDB.getKeySet().size()));
            deck.addCard(morphDB.getCard(id));
        }
        //isi dengan spell potion
        int potionCount = (int) (maxCard * POTION_PERCENTAGE);
        System.out.println("potion");
        CardDatabase potionDB = cf.getDatabase("Potion Spell");
        for(int i =0;i<potionCount;i++){
            int id = potionDB.getKeySet().get(random.nextInt(potionDB.getKeySet().size()));
            deck.addCard(potionDB.getCard(id));
        }
        //isi dengan spell swap
        int swapCount = (int) (maxCard * SWAP_PERCENTAGE);
        System.out.println("swap");
        CardDatabase swapDB = cf.getDatabase("Swap Spell");
        for(int i =0;i<swapCount;i++){
            int id = swapDB.getKeySet().get(random.nextInt(swapDB.getKeySet().size()));
        //    System.out.println(id);
            deck.addCard(swapDB.getCard(id));
         //   System.out.println(swapDB.getCard(id));
        }
        //cek jika belum pas
        int totalCount = charCount+morphCount+potionCount+swapCount;
        String[] cardType = {"Character","Morph Spell","Potion Spell","Swap Spell"};
        while(totalCount<maxCard){
            CardDatabase db = cf.getDatabase(cardType[random.nextInt(cardType.length)]);
            int id = db.getKeySet().get(random.nextInt(db.getKeySet().size()));
            deck.addCard(db.getCard(id));
            totalCount++;
        }
        //kalo kelebihan gak ketambah otomatis
        deck.shuffle();
    }
    public int getTurn(){
        return turns;
    }
    public void setTurn(int turn){
        turns = turn;
    }
    
    public void setup(){
        for(int i=0; i<2; i++) {
        	for(int j=0; j<INITIAL_CARD; j++) {
          //      System.out.println("aaa "+i);
        		this.players[i].addCard(this.deck[i].drawCard());
        	} // di sini harusnya draw 3 kartu dulu
          //  System.out.println("anying");
        }
        this.players[0].setHP(80);
        this.players[1].setHP(80);;// terus tiap player reset health
        
      //  publish(new PlayerChangedEvent(this.cur_player));
        // publish event playerchanged buat ngubah giliran player jadi giliran p1
        //init GUI
        Frame.getInstance().initPhase();

        stageController(this.phases[this.phase_id]);
        // terus pake stageController buat masuk fase draw
    }
    
    public Player getPlayer(int player_num) {
    	return this.players[player_num];
    }
    
    public int getCurPlayer() {
    	return this.cur_player;
    }
    
    public void stageController(Phase phase){
        if(phase == Phase.DRAW){
            System.out.println("asuscheofre");
            drawPhase();
        }else if(phase==Phase.PLAN){
            System.out.println("anying");
        }
        else if (phase == Phase.END){
            // end phase
        }
    }
    
    public void drawPhase() {
    	// manggil draw()
        // publish(new PhaseChangedEvent(this.phases[phase_id]));
      //  Card c1 = this.deck[cur_player].drawCard();
      //  Card c2 = this.deck[cur_player].drawCard();
      //  Card c3 = this.deck[cur_player].drawCard();
      //  publish(new EnterDrawPhaseEvent(c1, c2, c3));
        isDrawPhaseEnd = false;
        Frame.getInstance().drawPhase(isDrawPhaseEnd);
    }
    public void setDrawPhaseEndFlag(boolean flag){
        isDrawPhaseEnd = flag;
    }
    public boolean getDrawPhaseEndFlag(){
        return isDrawPhaseEnd;
    }
    public void endStage(){
        this.cur_player = (this.cur_player+1)%2;
        publish(new PlayerChangedEvent(this.cur_player) );
    }
    public void solveBattle() {
    	
    }
    
    public boolean canDirectAttack() {
    	// kalo kartu di tangan musuh abis
    	return this.players[(cur_player+1)%2].getIsDeckEmpty();
    }
    
    @Override
    public void onEvent(Event event){
        // kalo eventnya PhaseChangeEvent
    	if (event instanceof PhaseChangedEvent) {
    		stageController((Phase)event.getInfo());
    	}
    }
    
    @Override
    public void publish(Event event){
        this.channel.sendEvent(this, event);
    }
    public Deck getDeck(int idx){
        return deck[idx];
    }
}
