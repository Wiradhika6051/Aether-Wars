package com.aetherwars.GUI.Components;

import com.aetherwars.util.GlobalVar;
import com.aetherwars.util.Utility;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Component;

public class HealthBar extends JPanel{
    private int hp = 100;
    private String name;
    private float align = Component.LEFT_ALIGNMENT;
    private int Width;
    private int Height;
    private int Xcoor;
    private int Ycoor;
    private Border border;
  //  private Bar prb_health_bar;

    public HealthBar(String name, int hp, float align){
        this.name = name;
        this.align = align;
        this.hp = hp;
        this.Width = (int)((15/60.0)*GlobalVar.getScreenWidth());
        this.Height = (int)(1.5/40.0*GlobalVar.getScreenHeight());
        this.Xcoor = (int)(1/60.0*GlobalVar.getScreenWidth());
        this.Ycoor = (int)(2/40.0*GlobalVar.getScreenHeight());
        this.setBackground(Color.LIGHT_GRAY);
        this.setSize(new Dimension(Width,Height));
        this.setBounds(Xcoor,Ycoor,Width,Height);
        this.border = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(border);
        this.setLayout(null);
        this.setVisible(true);
    //    render();
    }
    /*
    public void render(){
        this.setLayout(new BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
    
        JPanel pnl_health_bar = new JPanel();
    
        JLabel lbl_name = new JLabel(" " + name + " ");
        prb_health_bar = new Bar(0,100);
        prb_health_bar.setValue(hp);
        prb_health_bar.setBackward(align == Component.RIGHT_ALIGNMENT);
        prb_health_bar.setPreferredSize(new Dimension(300,20));
        pnl_health_bar.add(prb_health_bar);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        
        pnl_health_bar.setAlignmentX(align);
        this.add(pnl_health_bar);
        lbl_name.setAlignmentX(align);
        this.add(lbl_name);

    }
    */
}
/*
class Bar extends JProgressBar{
    private boolean isBackward = false;

    public Bar(int min, int max){
        super(min,max);
    }

    public void setBackward(boolean b){
        isBackward = b;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(isBackward){
            Graphics2D g2d = (Graphics2D) g;
            g2d.scale(-1, 1); //Flips over y-axis
            g2d.translate(-getWidth(), 0); //Moves back to old position.
            
            super.paintComponent(g2d);
        }else{
            super.paintComponent(g);
        }
    }
}
*/