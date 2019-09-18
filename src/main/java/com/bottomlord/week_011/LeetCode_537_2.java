package com.bottomlord.week_011;

public class LeetCode_537_2 {
    public String complexNumberMultiply(String a, String b) {
        int ai = a.indexOf("+");
        int ax = Integer.parseInt(a.substring(0, ai));
        int ay = Integer.parseInt(a.substring(ai + 1, a.indexOf("i")));

        int bi = b.indexOf("+");
        int bx = Integer.parseInt(b.substring(0, bi));
        int by = Integer.parseInt(b.substring(bi + 1, b.indexOf("i")));

        return ax * bx - ay * by + "+" + (ax * by + bx * ay) + "i";
    }
}