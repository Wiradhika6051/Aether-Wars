package com.aetherwars.GUI.Components;

import com.aetherwars.util.GlobalVar;

import javax.swing.*;
import java.awt.*;

import static com.aetherwars.util.Utility.getFractionSize;

public class ShowManaLabel extends JPanel {
    private int  currentMana;
    private int maxMana;
    public JLabel manalabel;
    public JLabel manaLeftLabel;
    public ShowManaLabel(int currentMana,int maxMana){
        this.setLayout(null);
        this.currentMana = currentMana;
        this.maxMana = maxMana;
        this.manalabel = new JLabel("Mana");
        this.manaLeftLabel = new JLabel(this.currentMana+"/"+this.maxMana);
        manalabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 1, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 0.3, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 1, 40)
        );
        manalabel.setFont(new Font("Default",Font.PLAIN,26));
        manaLeftLabel.setFont(new Font("Default",Font.PLAIN,26));
        manaLeftLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 1.4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 1.6, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 1, 40)
        );
        this.setBackground(new java.awt.Color(200, 200, 200));
        add(manalabel);
        add(manaLeftLabel);
        setBorder(GlobalVar.blackLineBorder);
    }
    public void updateCurrentMana(int mana){
        this.currentMana = mana;
        this.manaLeftLabel.setText(this.currentMana+"/"+this.maxMana);
    }
    public void updateMaxMana(int maxMana){
        this.maxMana = maxMana;
        this.manaLeftLabel.setText(this.currentMana+"/"+this.maxMana);
    }
}
