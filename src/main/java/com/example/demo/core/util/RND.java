package com.example.demo.core.util;

import java.util.Random;

public class RND {
    public static final String eng = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALL_ENG = eng;
    public static final char[] ALL_ENG_CHARS = ALL_ENG.toCharArray();
    public static final Random rnd = new Random();
    public static String strEng(int len) {
        return strFrom(len, ALL_ENG_CHARS);
    }
    private static String strFrom(int len, char[] availableChars) {
        char[] charArray = new char[len];
        for (int i = 0; i < len; i++) {
            charArray[i] = availableChars[rnd.nextInt(availableChars.length)];
        }
        return new String(charArray);
    }
    public static void main(String[] args) {
        System.out.println(RND.strEng(20));
    }
}