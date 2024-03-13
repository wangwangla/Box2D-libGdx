package com.kangwang;

public class WorldConstant {
    public static float PPM = 10f;
    public static float convert(float x){
        return x / PPM;
    }
    public static float reconvert(float x){
        return x * PPM;
    }
}
