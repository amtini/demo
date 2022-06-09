package com.example;

public class LocalFunctions {
    public Integer PixelToInteger(String pixelString){
        return Integer.parseInt(pixelString.substring(0, pixelString.length()-2));
    }
}
