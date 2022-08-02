package com.aetherwars.GUI.Components;

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

public class HandCardLabel extends JPanel {
    private JLabel CardImage;
    private JLabel manaCostLabel;
    private JLabel statLabel;
    private ImageIcon picture;
    boolean isEnabled;

    public HandCardLabel(int manacost,String description,String imagePath){
        isEnabled = false;
        this.setLayout(null);
        setBorder(GlobalVar.blackLineBorder_2);
        setCharacter(manacost,description,imagePath);
        this.setBackground(new java.awt.Color(200, 200, 200,60));
        initMouseAdapter();
    }
    public void setEnabled(boolean isSelected){
        this.isEnabled=isSelected;
        if(isEnabled) {
            setBackground(new java.awt.Color(217, 234, 211,200));
        }
        else{
            setBackground(new java.awt.Color(200, 200, 200,60));
        }
    }
    public void setCharacter(int manacost,String description,String imagePath){
        //reset component
        if(picture!=null){
            removeAll();
        }
        //gambar kartu
        try {
            URL path = getClass().getResource(imagePath);
            BufferedImage original_image = ImageIO.read(new File(path.toURI()));
            picture = new ImageIcon(original_image);
            //resize image
            Image image = picture.getImage();
            Image new_img = image.getScaledInstance(
                    getFractionSize(GlobalVar.getScreenWidth(), 3, 60),
                    getFractionSize(GlobalVar.getScreenHeight(), 4, 40),
                    Image.SCALE_DEFAULT);
            picture = new ImageIcon(new_img);
        }
        catch(Exception e){
            System.out.println(e);
        }
        CardImage = new JLabel(picture);
        CardImage.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 0.75, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 0.75, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 5, 40)
        );
        add(CardImage);
        this.manaCostLabel = new JLabel("MANA "+manacost);
        manaCostLabel.setFont(new Font("Default",Font.PLAIN,18));
        manaCostLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 1.6, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 5.65, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 1.5, 40)
        );
        add(manaCostLabel);
        this.statLabel = new JLabel(description);
        statLabel.setFont(new Font("Default",Font.PLAIN,18));
        statLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(), 1.2 , 60),
                getFractionSize(GlobalVar.getScreenHeight(), 6.8, 40),
                getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 1.5, 40)
        );
        add(statLabel);
        revalidate();
        repaint();
    }
    void initMouseAdapter(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isEnabled = !isEnabled;
                setEnabled(isEnabled);
                com.aetherwars.view.Frame.getInstance().getContentPane().removeAll();
                com.aetherwars.view.Frame.getInstance().renderComponents(com.aetherwars.view.Frame.getInstance().getDebugMode());
                com.aetherwars.view.Frame.getInstance().revalidate();
                Frame.getInstance().repaint();
            }
        });
    }
}