package com.aetherwars.GUI.Components;

import com.aetherwars.card.Character.Character;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.util.GlobalVar;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.aetherwars.util.Utility.getFractionSize;

public class DescriptionPanel extends JPanel {
    JLabel description;
    public DescriptionPanel(){
        this.setBackground(new java.awt.Color(200, 200, 200));
        description = new JLabel("");
        description.setFont(new Font("Default",Font.ITALIC,20));
        description.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 0.5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 1, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 7.5, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 6, 40)
        );
        setBorder(GlobalVar.blackLineBorder);
        setText("<html><p>\"An enderman is a neutral mob found in all four dimensions. Endermen can teleport and pick up blocks.\"</p></html>");
        setLayout(null);
        initMouseListener();
    }
    public void setText(String desc){
        description.setText(desc);
    }
    public void initMouseListener(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                add(description);
                revalidate();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                remove(description);
                revalidate();
                repaint();
            }
        });
    }
}
