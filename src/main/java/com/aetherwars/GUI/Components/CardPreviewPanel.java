package com.aetherwars.GUI.Components;

import com.aetherwars.util.GlobalVar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import static com.aetherwars.util.Utility.getFractionSize;

public class CardPreviewPanel extends JPanel {
    private ImageIcon picture;
    private JLabel sprite;

    public CardPreviewPanel(){
        this.setLayout(null);
        showSprite(null);
        this.setBackground(new java.awt.Color(50, 50, 50));
    }
    public void showSprite(String imagePath){
        removeAll();
        if(imagePath==null){
            sprite = new JLabel();
        }
        else {
            //gambar kartu
            try {
                System.out.println(imagePath);
                URL path = getClass().getResource(imagePath);
                BufferedImage original_image = ImageIO.read(new File(path.toURI()));
                picture = new ImageIcon(original_image);
                //resize image
                Image image = picture.getImage();
                Image new_img = image.getScaledInstance(
                        getFractionSize(GlobalVar.getScreenWidth(), 4, 60),
                        getFractionSize(GlobalVar.getScreenHeight(), 9, 40),
                        Image.SCALE_DEFAULT);
                System.out.println("ehe");
                picture = new ImageIcon(new_img);
            } catch (Exception e) {
                System.out.println(e);
            }
            sprite = new JLabel(picture);
        }
        sprite.setBounds(
                0,
                0,
                getFractionSize(GlobalVar.getScreenWidth(), 6, 60),
                getFractionSize(GlobalVar.getScreenHeight(), 11, 40)
        );
        add(sprite);
    }
}
