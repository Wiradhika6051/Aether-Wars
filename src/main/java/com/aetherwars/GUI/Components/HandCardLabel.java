package com.aetherwars.GUI.Components;

import com.aetherwars.GUI.Selectable;
import com.aetherwars.Game;
import com.aetherwars.card.Card;
import com.aetherwars.util.GlobalVar;
import com.aetherwars.view.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import static com.aetherwars.util.Utility.getFractionSize;

public class HandCardLabel extends JPanel implements Selectable {
    private JLabel CardImage;
    private JLabel manaCostLabel;
    private JLabel statLabel;
    private ImageIcon picture;
    boolean isEnabled;
    boolean isRender;
    boolean isSelectable;
    boolean isDrawable;
    boolean isSelected;//udah dipilih kah buat dimasukkin
    Card card;

    public HandCardLabel(boolean isDrawable){
        isSelected = false;
        this.isDrawable = isDrawable;
        isEnabled = false;
        isRender = false;
        isSelectable = true;
        this.setLayout(null);
        setBorder(GlobalVar.blackLineBorder_2);
        if(isDrawable){
            this.setBackground(new java.awt.Color(200, 200, 200 ));
        }
        else {
            this.setBackground(new java.awt.Color(200, 200, 200, 60));
        }
        initMouseAdapter();
    }
    public boolean isRendered(){
        //menghasilkan true bila udah ditambahin chara
        return isRender;
    }
    public void setEnabled(boolean isSelected){
        this.isEnabled=isSelected;
        if(isEnabled) {
      //      setBackground(new java.awt.Color(217, 234, 211,200));
            setBackground(new java.awt.Color(217, 234, 211));
        }
        else{
            setBackground(isDrawable?new java.awt.Color(200, 200, 200):new java.awt.Color(200, 200, 200,60));
        }
    }
    public void setCharacter(Card card){
        //reset component
        if(picture!=null){
            removeAll();
        }
        isRender=true;
        this.card = card;
        //gambar kartu
        try {
            System.out.println(card.getImagepath());
            URL path = getClass().getResource(card.getImagepath());
            BufferedImage original_image = ImageIO.read(new File(path.toURI()));
            picture = new ImageIcon(original_image);
            //resize image
            Image image = picture.getImage();
            Image new_img = isDrawable?
                    image.getScaledInstance(
                    getFractionSize(GlobalVar.getScreenWidth(), 6, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 8, 40),
                    Image.SCALE_DEFAULT)
                    :
                    image.getScaledInstance(
                            getFractionSize(GlobalVar.getScreenWidth(), 3, 60),
                            getFractionSize(GlobalVar.getScreenHeight(), 4, 40),
                            Image.SCALE_DEFAULT)
                    ;
            picture = new ImageIcon(new_img);
        }
        catch(Exception e){
            System.out.println(e);
        }
        CardImage = new JLabel(picture);
        this.manaCostLabel = new JLabel("MANA "+card.getMana());
        this.statLabel = new JLabel(card.getStat());
        if(isDrawable){
            CardImage.setBounds(
                    getFractionSize(GlobalVar.getScreenWidth(), 0.75, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 0.75, 40),
                    getFractionSize(GlobalVar.getScreenWidth(), 8, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 10, 40)
            );
            manaCostLabel.setFont(new Font("Default",Font.BOLD,32));
            manaCostLabel.setBounds(
                    //         getFractionSize(GlobalVar.getScreenWidth(), 1.6, 60),
                    getFractionSize(GlobalVar.getScreenWidth(), 0.75, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 12, 40),
                    getFractionSize(GlobalVar.getScreenWidth(), 6, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 1.5, 40)
            );
            statLabel.setFont(new Font("Default",Font.PLAIN,32));
            statLabel.setBounds(
                    //       getFractionSize(GlobalVar.getScreenWidth(), 1.2 , 60),
                    getFractionSize(GlobalVar.getScreenWidth(), 0.75, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 15, 40),
                    getFractionSize(GlobalVar.getScreenWidth(), 10, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 1.5, 40)
            );
        }
        else {
            CardImage.setBounds(
                    getFractionSize(GlobalVar.getScreenWidth(), 0.75, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 0.75, 40),
                    getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 5, 40)
            );
            manaCostLabel.setFont(new Font("Default",Font.BOLD,18));
            manaCostLabel.setBounds(
                    //         getFractionSize(GlobalVar.getScreenWidth(), 1.6, 60),
                    getFractionSize(GlobalVar.getScreenWidth(), 0.4, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 5.65, 40),
                    getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 1.5, 40)
            );
            statLabel.setFont(new Font("Default",Font.PLAIN,18));
            statLabel.setBounds(
                    //       getFractionSize(GlobalVar.getScreenWidth(), 1.2 , 60),
                    getFractionSize(GlobalVar.getScreenWidth(), 0.4, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 6.8, 40),
                    getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 1.5, 40)
            );
        }
        add(CardImage);
        add(manaCostLabel);
        add(statLabel);
        revalidate();
        repaint();
    }

    @Override
    public void setSelectability(boolean selectability) {
        isSelectable = selectability;
    }
    public void addToDeck(){
        Game.getGameManager().getPlayer(Game.getGameManager().getCurPlayer()).addCard(card);
        Frame.getInstance().reset(Frame.getInstance().getDebugMode());
    }

    public void removeCard(){
        isRender = false;
    }
    void initMouseAdapter(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!isSelectable && !isDrawable)return;
                isEnabled = !isEnabled;
                setEnabled(isEnabled);
                if(isDrawable){
                    if(!isSelected) {
                        isSelected = true;
                    }
                    else{
                        addToDeck();
                        isSelected = false;
                        Game.getGameManager().setDrawPhaseEndFlag(true);
                        com.aetherwars.view.Frame.getInstance().drawPhase(true);
                        Frame.getInstance().renderComponents(Frame.getInstance().getDebugMode());
                    }
                }
                com.aetherwars.view.Frame.getInstance().getContentPane().removeAll();
                com.aetherwars.view.Frame.getInstance().renderComponents(com.aetherwars.view.Frame.getInstance().getDebugMode());
                com.aetherwars.view.Frame.getInstance().revalidate();
                Frame.getInstance().repaint();
            }
        });
    }
}