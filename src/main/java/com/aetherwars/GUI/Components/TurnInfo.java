package com.aetherwars.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class TurnInfo extends JPanel {
    int turn=0;
    public TurnInfo(){
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //gambar circle
        g.setColor(Color.lightGray);
        g.fillOval(0,0,g.getClipBounds().width,g.getClipBounds().width);
        //gambar tulisan
        g.setFont(g.getFont().deriveFont(20f));
        g.setColor(Color.BLACK);
        g.drawString("Turn "+this.turn,(int)(g.getClipBounds().width*0.28),(int)(g.getClipBounds().height*0.53));
    }
    public void updateTurn(int turn){
        this.turn = turn;
    }
}
