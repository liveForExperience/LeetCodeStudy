package com.bottomlord.week_011;

public class LeetCode_537_1_复数乘法 {
    public String complexNumberMultiply(String a, String b) {
        String regex = "\\+\\|i";
        String[] as = a.split(regex);
        String[] bs = b.split(regex);

        int ax = Integer.parseInt(as[0]);
        int ay = Integer.parseInt(as[1]);
        int bx = Integer.parseInt(bs[0]);
        int by = Integer.parseInt(bs[1]);

        return ax * bx - ay * by + "+" + (ax * by + bx * ay) + "i";
    }
}
