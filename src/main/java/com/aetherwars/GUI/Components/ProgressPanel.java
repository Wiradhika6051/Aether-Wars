package com.aetherwars.GUI.Components;

import com.aetherwars.util.GlobalVar;

import javax.swing.*;
import java.awt.*;

import static com.aetherwars.util.Utility.getFractionSize;

public class ProgressPanel extends JPanel {
    JLabel phaseLabel;
    boolean selected;
    public ProgressPanel(String phase) {
        setBackground(new java.awt.Color(200, 200, 200,120));
        setBorder(GlobalVar.blackLineBorder);
        phaseLabel = new JLabel(phase);
        phaseLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),1,60) ,
                0,
                getFractionSize(GlobalVar.getScreenWidth(),13,60) ,
                getFractionSize(GlobalVar.getScreenHeight(),5,40)
        );
        phaseLabel.setFont(new Font("Default",Font.BOLD,18));
        add(phaseLabel);
        selected = false;
    }
    public void setSelected(boolean isSelected){
        if(isSelected){
            setBackground(Color.orange);
        }
        else{
            setBackground(new java.awt.Color(200, 200, 200,120));
        }
    }
}
