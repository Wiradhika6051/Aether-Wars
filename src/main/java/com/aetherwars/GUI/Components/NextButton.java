package com.aetherwars.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.aetherwars.model.Phase;
import com.aetherwars.view.Frame;

public class NextButton extends JButton {
    Phase curState;
    public NextButton() {
        curState = Phase.DRAW;
        setText(">>");
        setFocusable(false);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(225, 225, 225));
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                setForeground(Color.BLACK);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(new java.awt.Color(0, 0, 0));
                setForeground(new java.awt.Color(225, 225, 225));
            }

        });
        addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Frame instance = Frame.getInstance();
                if(curState==Phase.DRAW) {
                    curState = Phase.PLAN;
                    instance.getStateGUI("DRAW").setSelected(false);
                    instance.getStateGUI("PLAN").setSelected(true);
                }
                else if(curState==Phase.PLAN){
                    curState = Phase.ATTACK;
                    instance.getStateGUI("PLAN").setSelected(false);
                    instance.getStateGUI("ATTACK").setSelected(true);
                }
                else if(curState==Phase.ATTACK){
                    curState = Phase.END;
                    instance.getStateGUI("ATTACK").setSelected(false);
                    instance.getStateGUI("END").setSelected(true);
                }
                else if(curState==Phase.END){
                    curState = Phase.DRAW;
                    instance.getStateGUI("END").setSelected(false);
                    instance.getStateGUI("DRAW").setSelected(true);
                }
                instance.revalidate();
                instance.repaint();
            }
        }

        );

    }
}
