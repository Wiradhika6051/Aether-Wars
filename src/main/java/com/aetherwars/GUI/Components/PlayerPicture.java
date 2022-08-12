package com.aetherwars.GUI.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class PlayerPicture extends JPanel {
    private ImageIcon picture;
    private JLabel sprite;

    public PlayerPicture(String imagePath){
        setLayout(null);
        //gambar kartu
        try {
          //  System.out.println(imagePath);
            URL path = getClass().getResource(imagePath);
            BufferedImage original_image = ImageIO.read(new File(path.toURI()));
            picture = new ImageIcon(original_image);
            //resize image
            Image image = picture.getImage();
            Image new_img = image.getScaledInstance(110,110,Image.SCALE_DEFAULT);
            picture = new ImageIcon(new_img);
            sprite = new JLabel(picture);
            sprite.setBounds(0,0,picture.getIconWidth(),picture.getIconHeight());
            add(sprite);
        }
        catch(Exception e){
            System.out.println(e);
        }
      //  System.out.println("a+"+picture);
  //      sprite = new JLabel(picture);
    }
}
