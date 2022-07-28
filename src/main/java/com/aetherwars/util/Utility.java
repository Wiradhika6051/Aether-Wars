package com.aetherwars.util;

public class Utility {
    public static int getFractionSize(double base,double pembilang,double penyebut){
        return (int)(base*((1.0*pembilang)/penyebut));
    }
}
