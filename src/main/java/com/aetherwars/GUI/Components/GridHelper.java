package com.aetherwars.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class GridHelper extends JPanel {
    //debug grid to see grid
    double screenWidth;
    double screenHeight;
    public GridHelper(double screenWidth,double screenHeight) {
        this.screenWidth= screenWidth;
        this.screenHeight = screenHeight;
   //     System.out.println(screenWidth+" "+screenHeight);
        this.setSize(new Dimension((int)this.screenWidth,(int)this.screenHeight));
        this.setBounds(0,0,(int)this.screenWidth,(int)this.screenHeight);
        this.setLayout(null);
        render();
    }
    public void render(){
        System.out.println(this.getWidth());
        for (int i = 0; i < 60; i++) {
            JLabel label = new JLabel(Integer.toString(i));
         //   System.out.println((int) (this.screenWidth * (i / 40.0)));
            label.setBounds((int) (this.screenWidth * (i / 60.0)), 200, 50, 50);
            add(label);
        }
        for (int i = 0; i < 40; i++) {
            JLabel label = new JLabel(Integer.toString(i));
        //    System.out.println((int) (this.screenHeight * (i / 40.0)));
            label.setBounds(470, (int) (this.screenHeight * (i / 40.0)), 50, 50);
            add(label);
        }
        this.setVisible(true);
    }
}
