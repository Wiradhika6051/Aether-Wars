package com.aetherwars.GUI.Components;

import com.aetherwars.util.GlobalVar;

import javax.swing.*;
import java.awt.*;

public class GridHelper extends JPanel {
    //debug grid to see grid
    int screenWidth;
    int screenHeight;
    public GridHelper() {
        this.screenWidth= GlobalVar.getScreenWidth();
        this.screenHeight = GlobalVar.getScreenHeight();
        this.setBounds(0,0,(int)this.screenWidth,(int)this.screenHeight);
        this.setLayout(null);
        setOpaque(false);
        render();
    }
    public void render(){
        System.out.println(this.getWidth());
        for (int i = 0; i < 60; i++) {
            JLabel label = new JLabel(Integer.toString(i));
         //   System.out.println((int) (this.screenWidth * (i / 40.0)));
            label.setBounds((int) (this.screenWidth * (i / 60.0)), GlobalVar.getScreenHeight()/2, 50, 50);
            add(label);
        }
        for (int i = 0; i < 40; i++) {
            JLabel label = new JLabel(Integer.toString(i));
        //    System.out.println((int) (this.screenHeight * (i / 40.0)));
            label.setBounds(GlobalVar.getScreenWidth()/3, (int) (this.screenHeight * (i / 40.0))-(int)(this.screenHeight*1/60), 50, 50);
            add(label);
        }
        this.setVisible(true);
    }
}
