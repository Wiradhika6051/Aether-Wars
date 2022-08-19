package com.aetherwars.GUI.Components;

import com.aetherwars.Game;
import com.aetherwars.util.GlobalVar;
import com.aetherwars.view.Frame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.aetherwars.util.Utility.getFractionSize;

public class RemovePrompt extends JPanel {
    static final String YES_PROMPT = "IYA";
    static final String NO_PROMPT = "TIDAK";
    //isinya background gelap dan warning sign
    ShadowyScreen bg;
    JPanel prompt;
    String text;
    JLabel message;
    JButton yesButton,noButton;
    int idx;
    public RemovePrompt(String text){
        setLayout(null);
        this.text = "<html>"+text+"</html>";
        initPrompt();
        initBG();
    }
    public void setIndexToBeRemoved(int idx){
        this.idx = idx;
    }
    void initPrompt(){
        initButton();
        message = new JLabel(text);
        message.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),21.5,60),
                getFractionSize(GlobalVar.getScreenHeight(),11,40),
                getFractionSize(GlobalVar.getScreenWidth(),18,60),
                getFractionSize(GlobalVar.getScreenHeight(),3,40)
        );
        message.setFont(new Font("Default",Font.BOLD,32));
        add(message);
        prompt = new JPanel();
        prompt.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),20,60),
                getFractionSize(GlobalVar.getScreenHeight(),10,40),
                getFractionSize(GlobalVar.getScreenWidth(),20,60),
                getFractionSize(GlobalVar.getScreenHeight(),10,40)
        );
        prompt.setBorder(GlobalVar.blackLineBorder_2);
        prompt.setBackground(Color.WHITE);
        add(prompt);
    }
    void initButton(){
        yesButton = new JButton(YES_PROMPT);
        yesButton.setFont(new Font("Default",Font.BOLD,20));
        yesButton.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),22,60),
                getFractionSize(GlobalVar.getScreenHeight(),17,40),
                getFractionSize(GlobalVar.getScreenWidth(),4,60),
                getFractionSize(GlobalVar.getScreenHeight(),2,40)
        );
        yesButton.setFocusable(false);
        add(yesButton);
        noButton = new JButton(NO_PROMPT);
        noButton.setFont(new Font("Default",Font.BOLD,20));
        noButton.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),34,60),
                getFractionSize(GlobalVar.getScreenHeight(),17,40),
                getFractionSize(GlobalVar.getScreenWidth(),4,60),
                getFractionSize(GlobalVar.getScreenHeight(),2,40)
        );
        noButton.setFocusable(false);
        add(noButton);
        initButtonListener();
    }
    void initButtonListener(){
        noButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        yesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getGameManager().getPlayer(Game.getGameManager().getCurPlayer()).removeCard(idx);
                Frame.getInstance().reset(Frame.getInstance().getDebugMode());
                Frame.getInstance().getContentPane().removeAll();
                Frame.getInstance().renderComponents(Frame.getInstance().getDebugMode());
            }
        });
    }
    void initBG(){
        bg = new ShadowyScreen();
        bg.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),0,60),
                getFractionSize(GlobalVar.getScreenHeight(),0,40),
                getFractionSize(GlobalVar.getScreenWidth(),60,60),
                getFractionSize(GlobalVar.getScreenHeight(),40,40)
        );
        add(bg);
    }
    public void prompt(){
        int confirmation = JOptionPane.showConfirmDialog(
                Frame.getInstance(),
                "Apakah Anda yakin ingin membuang kartu?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );
    }
}
