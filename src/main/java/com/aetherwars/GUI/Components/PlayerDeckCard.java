package com.aetherwars.GUI.Components;

import com.aetherwars.card.Character.Character;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.util.GlobalVar;
import com.aetherwars.util.Utility;
import com.aetherwars.view.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import static com.aetherwars.util.Utility.getFractionSize;

public class PlayerDeckCard extends JPanel {
    private SummonedCharacter chara;
    public boolean isSelected;
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
        this.isSelected=false;
        this.deckNumber = deckNumber;
        initializeDeckCard();
        initMouseListener();
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
        this.chara = null;
        this.attackIcon = loadAsset("/com/aetherwars/card/image/icon/attack.png",15,15);
        this.attackIconLabel = new JLabel(this.attackIcon);
        this.attackIconLabel.setBounds(
                0,
                0,
                getFractionSize(GlobalVar.getScreenWidth(),0.5,60),
                getFractionSize(GlobalVar.getScreenWidth(),0.5,60)
        );
        this.healthIcon = loadAsset("/com/aetherwars/card/image/icon/health.png",15,15);
        this.healthIconLabel = new JLabel(this.healthIcon);
        this.healthIconLabel.setBounds(
                0,
                0,
                getFractionSize(GlobalVar.getScreenWidth(),0.5,60),
                getFractionSize(GlobalVar.getScreenWidth(),0.5,60)
        );
    }

    public void addCharacter(SummonedCharacter c){
        this.chara = c;
        //muat sprite
        this.sprite = loadAsset(c.getBaseCard().getImagepath(),95,95);
        //tampilin jumlah attack
        this.attackLabel = new JLabel(Integer.toString(c.getAttack()));
        this.attackLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),0.42,60),
                getFractionSize(GlobalVar.getScreenHeight(),0.85,40),
                getFractionSize(GlobalVar.getScreenWidth(),2,60),
                getFractionSize(GlobalVar.getScreenHeight(),1,40)
        );
        add(attackLabel);
        //label jumlah health
        this.healthLabel = new JLabel(Float.toString(c.getHealth()));
        this.healthLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),3.4,60),
                getFractionSize(GlobalVar.getScreenHeight(),0.85,40),
                getFractionSize(GlobalVar.getScreenWidth(),2,60),
                getFractionSize(GlobalVar.getScreenHeight(),1,40)
        );
        add(healthLabel);
        //model gambarnya
        this.spriteLabel = new JLabel(this.sprite);
        this.spriteLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),0.3,60),
                getFractionSize(GlobalVar.getScreenHeight(),-0.5,40),
                getFractionSize(GlobalVar.getScreenWidth(),4,60),
                getFractionSize(GlobalVar.getScreenHeight(),4.3,40)
        );
        add(spriteLabel);
        //exp dan level
        this.levelLabel = new JLabel(c.getExp()+"/"+c.getMaxExpToNextLevel()+" ["+c.getLevel()+"]");
        this.levelLabel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),1.7,60),
                getFractionSize(GlobalVar.getScreenHeight(),3,40),
                getFractionSize(GlobalVar.getScreenWidth(),3,60),
                getFractionSize(GlobalVar.getScreenHeight(),1.8,40)
        );
        add(levelLabel);
        //tampilin icon health
        this.healthPanel = new JPanel();
        this.healthPanel.setLayout(null);
        this.healthPanel.add(healthIconLabel);
        this.healthPanel.setBackground(new java.awt.Color(200, 200, 200));
        this.healthPanel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),3.5,60),
                getFractionSize(GlobalVar.getScreenHeight(),0.4,40),
                getFractionSize(GlobalVar.getScreenWidth(),0.5,60),
                getFractionSize(GlobalVar.getScreenWidth(),0.5,60)
        );
        add(this.healthPanel);
        //tambahin icon attack
        this.attackPanel = new JPanel();
        this.attackPanel.setLayout(null);
        this.attackPanel.add(attackIconLabel);
        this.attackPanel.setBackground(new java.awt.Color(200, 200, 200));
        this.attackPanel.setBounds(
                getFractionSize(GlobalVar.getScreenWidth(),0.45,60),
                getFractionSize(GlobalVar.getScreenHeight(),0.4,40),
                getFractionSize(GlobalVar.getScreenWidth(),0.5,60),
                getFractionSize(GlobalVar.getScreenWidth(),0.5,60)
        );
        add(this.attackPanel);
        //hapus kode decknya
        remove(deckLabel);
        //cetak ulang
        this.revalidate();
        this.repaint();
    }
    public void removeCharacter(){
        add(deckLabel);
        this.chara = null;
        this.attackIcon = null;
        remove(attackIconLabel);
        this.healthIcon = null;
        remove(healthIconLabel);
        remove(attackLabel);
        remove(healthLabel);
        this.sprite = null;
        remove(spriteLabel);
        remove(levelLabel);
        remove(healthPanel);
        remove(attackPanel);
        setSelected(false);
        this.revalidate();
        this.repaint();
    }

    public void setSelected(boolean isSelected){
        if(this.chara!=null && isSelected) {
            this.setBackground(new java.awt.Color(217, 234, 211));
            this.attackPanel.setBackground(new java.awt.Color(217, 234, 211));
            this.healthPanel.setBackground(new java.awt.Color(217, 234, 211));
            Border border = BorderFactory.createLineBorder(new java.awt.Color(251, 180, 84),4);
            this.setBorder(border);
        }
        else if(isSelected && this.chara==null){
            this.setBackground(new java.awt.Color(251, 180, 84,70));
       //     this.setBackground(new java.awt.Color(255, 213, 128,70));
       //     this.attackPanel.setBackground(new java.awt.Color(217, 234, 211));
        //    this.healthPanel.setBackground(new java.awt.Color(217, 234, 211));
      //      this.deckLabel.setForeground(Color.blue);
            Border border = BorderFactory.createLineBorder(new Color(0, 255, 0,255),4);
         //   Border border = BorderFactory.createLineBorder(new java.awt.Color(217, 234, 211));
            this.setBorder(border);
        }
        else{
            if(chara!=null) {
                this.attackPanel.setBackground(new java.awt.Color(200, 200, 200));
                this.healthPanel.setBackground(new java.awt.Color(200, 200, 200));
            }
            else{
                add(deckLabel);
            }
            this.setBackground(new java.awt.Color(200, 200, 200));
            Border border = BorderFactory.createLineBorder(Color.BLACK,1);
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
    private void initMouseListener(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("ehe"+isSelected);
                isSelected = !isSelected;
                setSelected(isSelected);
                Frame.getInstance().getContentPane().removeAll();
                Frame.getInstance().renderComponents(Frame.getInstance().getDebugMode());
                Frame.getInstance().revalidate();
                Frame.getInstance().repaint();
            }
        });
    }
}
