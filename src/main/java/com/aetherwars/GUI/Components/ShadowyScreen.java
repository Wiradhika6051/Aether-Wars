package com.aetherwars.GUI.Components;

import com.aetherwars.util.GlobalVar;

import javax.swing.*;
import java.awt.*;

import static com.aetherwars.util.Utility.getFractionSize;

public class ShadowyScreen extends JPanel {
    public ShadowyScreen(){
        setLayout(null);
        setBackground(new Color(0,0,0,120));
    }
    /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0,0,0,120));
        g.fillRect(0,0,GlobalVar.getScreenWidth(),100);
   }*/
}
