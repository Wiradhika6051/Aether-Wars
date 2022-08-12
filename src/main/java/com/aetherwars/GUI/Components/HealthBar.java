package com.aetherwars.GUI.Components;

import com.aetherwars.util.GlobalVar;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class HealthBar extends JProgressBar {
    private int hp;
    private int maxHP;
    private float align;

    public HealthBar(int hp,int maxHP, float align) {
        this.align = align;
        this.maxHP = maxHP;
        this.hp = hp;
        this.setBorder(GlobalVar.blackLineBorder);
        setMaximum(maxHP);
        if(align==Component.RIGHT_ALIGNMENT){
            reverse();
        }
        else {
            this.setBackground(Color.RED);
            this.setForeground(Color.GREEN);
            this.setUI( new BasicProgressBarUI() {
                protected Color getSelectionBackground() {
                    return Color.white;
                }
                protected Color getSelectionForeground() {
                    return Color.black;
                }
            } );
            this.setValue(this.hp);
            this.setString(this.hp + "/" + this.maxHP);
        }
        this.setStringPainted(true);
        this.setLayout(null);
        this.setVisible(true);
    }
    public void updateHP(int HP){
        this.hp = HP;
        int shownvalue = align==Component.RIGHT_ALIGNMENT?this.maxHP-this.hp:this.hp;
        this.setValue(shownvalue);
        this.setString(this.hp + "/" + this.maxHP);
    }
    public void updateMaxHP(int MaxHP){
        this.maxHP = MaxHP;
        setMaximum(MaxHP);
    }
    public void reverse(){
        int shownValue = this.maxHP-this.hp;
        this.setValue(shownValue);
        this.setString(this.hp + "/" + this.maxHP);
        this.setBackground(Color.GREEN);
        this.setForeground(Color.RED);
        this.setUI( new BasicProgressBarUI() {
            protected Color getSelectionBackground() {
                return Color.black;
            }
            protected Color getSelectionForeground() {
                return Color.white;
            }
        } );
    }
}