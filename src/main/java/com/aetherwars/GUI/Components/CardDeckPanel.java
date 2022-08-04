package com.aetherwars.GUI.Components;

import com.aetherwars.util.GlobalVar;

import javax.swing.*;

import java.awt.*;

import static com.aetherwars.util.Utility.getFractionSize;

public class CardDeckPanel extends JPanel {
    private int  currentCard;
    private int maxCard;
    public JLabel decklabel;
    public JLabel cardLeftLabel;
    public CardDeckPanel(int currentCard,int maxCard) {
        this.setLayout(null);
        this.currentCard = currentCard;
        this.maxCard = maxCard;
        this.decklabel = new JLabel("Deck");
        decklabel.setFont(new Font("Default",Font.PLAIN,26));
        decklabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 1, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 0.3, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 1, 40)
        );
        add(decklabel);
        this.cardLeftLabel = new JLabel(this.currentCard + "/" + this.maxCard);
        cardLeftLabel.setFont(new Font("Default",Font.PLAIN,26));
        cardLeftLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 1, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 1.6, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 1, 40)
        );
        add(cardLeftLabel);
        this.setBackground(new java.awt.Color(200, 200, 200));
        setBorder(GlobalVar.blackLineBorder);
    }

    public void updateDeck(int card){
        this.currentCard = card;
        this.cardLeftLabel.setText(this.currentCard+"/"+this.maxCard);
    }

}
