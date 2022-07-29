package com.aetherwars.util;

import jdk.nashorn.internal.objects.Global;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GlobalVar {
    public static Border blackLineBorder =  BorderFactory.createLineBorder(Color.BLACK);
    public static int getScreenWidth(){
        return (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    public static int getScreenHeight(){
        return (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }

}
