package com.aetherwars.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.aetherwars.GUI.Selectable;
import com.aetherwars.model.Phase;
import com.aetherwars.view.Frame;

public class NextButton extends JButton implements Selectable {
    Phase curState;
    boolean isSelectable;
    public NextButton() {
        isSelectable = true;
        curState = Phase.DRAW;
        setText(">>");
        setFocusable(false);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(225, 225, 225));
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                if(!isSelectable)return;
                setForeground(Color.BLACK);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(!isSelectable)return;
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

    @Override
    public void setSelectability(boolean selectability) {
        setEnabled(selectability);
        isSelectable = selectability;
    }
}
