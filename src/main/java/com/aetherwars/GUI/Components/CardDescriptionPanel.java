package com.aetherwars.GUI.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import com.aetherwars.GUI.Selectable;
import com.aetherwars.card.Character.Character;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.util.GlobalVar;

import static com.aetherwars.util.Utility.getFractionSize;

public class CardDescriptionPanel extends JPanel  implements Selectable{
    private JLabel information;
    private JLabel cardName;
    SummonedCharacter chara;
    boolean isSelectable;
  // private JLabel description;
  //  public JSeparator separator;

    public CardDescriptionPanel(){
        isSelectable = true;
        this.setLayout(null);
        chara = null;
     //   this.description = new JLabel("<html><p>""+chara.getBaseCard().getDescription()+"\"</p></html>");
        this.setBackground(new java.awt.Color(200, 200, 200));
     //   this.separator = new JSeparator((SwingConstants.VERTICAL));
     //   this.separator.setBackground(Color.black);
        setBorder(GlobalVar.blackLineBorder);
        initMouseListener();
    }


    public void showDescription(SummonedCharacter chara){
        this.chara = chara;
        if(this.chara!=null){
            //gambar judul
            cardName = new JLabel(chara.getBaseCard().getName());
            cardName.setBounds(
                    getFractionSize(GlobalVar.getScreenWidth(),1,60),
                    getFractionSize(GlobalVar.getScreenHeight(),0.8,40),
                    getFractionSize(GlobalVar.getScreenWidth(),10,60),
                    getFractionSize(GlobalVar.getScreenHeight(),2,40)
            );
            cardName.setFont(new Font("Default",Font.BOLD,32));
            add(cardName);
            //gambar kartu
            String informationtext = "<html><p>ATK : "+chara.getAttack()+
                    "</p><p>HP : "+chara.getHealth()+"</p><p>Level : "+chara.getLevel()+"</p><p>EXP: "+
                    chara.getExp()+"/"+chara.getMaxExpToNextLevel()+"</p><p>Type : "+chara.getBaseCard().getType()+
                    "</p></html>";
            this.information = new JLabel(informationtext);
            information.setBounds(
                    getFractionSize(GlobalVar.getScreenWidth(),1,60),
                    getFractionSize(GlobalVar.getScreenHeight(),1,40),
                    getFractionSize(GlobalVar.getScreenWidth(),10,60),
                    getFractionSize(GlobalVar.getScreenHeight(),8,40)
            );
            information.setFont(new Font("Default",Font.PLAIN,18));
            add(information);
        }
    }
    public void initMouseListener(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!isSelectable)return;
                if(chara!=null) {
                  /*  showDescription(new SummonedCharacter
                            (new Character(
                                    1,
                                    "Enderman",
                                    100,
                                    "Penghuni The End",
                                    "/com/aetherwars/card/image/character/Enderman.png",
                                    com.aetherwars.card.Character.Type.END,
                                    10,
                                    2,
                                    12,
                                    2
                            ),
                                    2
                            )
                    );*/
                    showDescription(chara);
                }
                revalidate();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!isSelectable)return;
                chara=null;
                try {
                    remove(cardName);
                    remove(information);
                }
                catch(Exception a){}
                revalidate();
                repaint();
            }
        });
    }

    @Override
    public void setSelectability(boolean selectability) {
        isSelectable = selectability;
    }
}
