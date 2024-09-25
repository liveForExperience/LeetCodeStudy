package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/9 12:49
 */
public class Interview_58II_2 {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}