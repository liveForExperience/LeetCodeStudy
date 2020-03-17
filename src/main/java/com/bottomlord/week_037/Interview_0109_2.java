package com.bottomlord.week_037;

/**
 * @author ThinkPad
 * @date 2020/3/17 8:47
 */
public class Interview_0109_2 {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s2 + s2).contains(s1);
    }
}