package com.aetherwars.GUI.Components;

import com.aetherwars.card.Character.Character;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.util.GlobalVar;
import com.aetherwars.util.Utility;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import static com.aetherwars.util.Utility.getFractionSize;

public class PlayerDeckCard extends JPanel {
    private SummonedCharacter chara;
    private String deckNumber;
  //  public javax.swing.GroupLayout cardDescriptionLayout;
    //kalau gak ada kartu
    private JLabel deckLabel;
    //kalau ada kartu
    private ImageIcon attackIcon;
    private ImageIcon healthIcon;
    private JLabel attackLabel;
    private JLabel healthLabel;
    private ImageIcon sprite;
    private JLabel spriteLabel;
    private JLabel levelLabel;
    private JLabel attackIconLabel;
    private JLabel healthIconLabel;
    private JPanel attackPanel;
    private JPanel healthPanel;
    public PlayerDeckCard(String deckNumber){
        setLayout(null);
        this.deckNumber = deckNumber;
        initializeDeckCard();
    }
    public void initializeDeckCard(){
        this.deckLabel = new JLabel(deckNumber);
        this.deckLabel.setFont(new Font("Serif", Font.PLAIN, 43));
        System.out.println("gomene");
        this.deckLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),1.8,60),
                getFractionSize(GlobalVar.getScreenHeight(),1.25,40),
                getFractionSize(GlobalVar.getScreenWidth(),2,60),
                getFractionSize(GlobalVar.getScreenHeight(),2,40)
        );
        add(deckLabel);
        this.setBackground(new java.awt.Color(200, 200, 200));
        this.setBorder(GlobalVar.blackLineBorder);
    }

    public void addCharacter(SummonedCharacter c){
        this.chara = c;
        this.attackIcon = loadAsset("/com/aetherwars/card/image/icon/attack.png",15,15);
        this.attackIconLabel = new JLabel(this.attackIcon);
        this.healthIcon = loadAsset("/com/aetherwars/card/image/icon/health.png",15,15);
        this.healthIconLabel = new JLabel(this.healthIcon);
        this.attackLabel = new JLabel(Integer.toString(c.getAttack()));
        this.healthLabel = new JLabel(Float.toString(c.getHealth()));
        this.sprite = loadAsset(c.getBaseCard().getImagepath(),80,70);
        this.spriteLabel = new JLabel(this.sprite);
        this.levelLabel = new JLabel(c.getExp()+"/"+c.getMaxExpToNextLevel()+" ["+c.getLevel()+"]");
        this.deckLabel = null;
        this.healthPanel = new JPanel();
        this.healthPanel.setLayout(null);
        this.healthPanel.setBackground(new java.awt.Color(200, 200, 200));
        this.attackPanel = new JPanel();
        this.attackPanel.setLayout(null);
        this.attackPanel.setBackground(new java.awt.Color(200, 200, 200));
        this.revalidate();
        this.repaint();
    }
    public void removeCharacter(){
        this.chara = null;
        this.attackIcon = null;
        this.attackIconLabel = null;
        this.healthIcon = null;
        this.healthIconLabel = null;
        this.attackLabel = null;
        this.healthLabel = null;
        this.sprite = null;
        this.spriteLabel = null;
        this.levelLabel = null;
        this.healthPanel = null;
        this.attackPanel = null;
        this.deckLabel = new JLabel(deckNumber);
        this.deckLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        setSelected(false);
        this.revalidate();
        this.repaint();
    }

    public void setSelected(boolean isSelected){
        if(this.chara!=null && isSelected) {
            this.setBackground(new java.awt.Color(217, 234, 211));
            this.attackPanel.setBackground(new java.awt.Color(217, 234, 211));
            this.healthPanel.setBackground(new java.awt.Color(217, 234, 211));
            Border border = BorderFactory.createLineBorder(new java.awt.Color(251, 180, 84));
            this.setBorder(border);
        }
        else if(!isSelected){
            this.setBackground(new java.awt.Color(200, 200, 200));
            this.attackPanel.setBackground(new java.awt.Color(200, 200, 200));
            this.healthPanel.setBackground(new java.awt.Color(200, 200, 200));
            Border border = BorderFactory.createLineBorder(Color.BLACK);
            this.setBorder(border);
        }
    }


    private ImageIcon loadAsset(String imagePath,int scaledWith,int scaledHeight){
        Image new_img = null;
        try {
            System.out.println(imagePath);
            URL path = getClass().getResource(imagePath);
            BufferedImage original_image = ImageIO.read(new File(path.toURI()));
            ImageIcon image_pre = new ImageIcon(original_image);
            //resize image
            Image image = image_pre.getImage();
            new_img = image.getScaledInstance(scaledWith,scaledHeight,Image.SCALE_DEFAULT);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return new ImageIcon(new_img);
    }

}
