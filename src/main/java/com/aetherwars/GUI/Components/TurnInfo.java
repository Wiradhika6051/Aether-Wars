package com.aetherwars.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class TurnInfo extends JPanel {
    int turn;
    public TurnInfo(int turn){
        this.turn = turn;
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.lightGray);
        g.fillOval(0,0,g.getClipBounds().width,g.getClipBounds().height);
        g.setFont(g.getFont().deriveFont(20f));
        g.setColor(Color.BLACK);
        g.drawString("Turn "+this.turn,(int)(g.getClipBounds().width*0.3),(int)(g.getClipBounds().height*0.53));
    }
}
