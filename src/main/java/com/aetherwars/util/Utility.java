package com.aetherwars.util;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Utility {
    public static int getFractionSize(double base,double pembilang,double penyebut){
        return (int)(base*((1.0*pembilang)/penyebut));
    }
}
