package com.aetherwars.util;

import jdk.nashorn.internal.objects.Global;

import java.awt.*;

public class GlobalVar {
    public static int getScreenWidth(){
        return (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    public static int getScreenHeight(){
        return (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
}
