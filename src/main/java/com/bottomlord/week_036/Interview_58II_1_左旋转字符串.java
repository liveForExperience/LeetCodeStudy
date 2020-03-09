package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/9 12:38
 */
public class Interview_58II_1_左旋转字符串 {
    public String reverseLeftWords(String s, int n) {
        StringBuilder left = new StringBuilder(), right = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i < n) {
                left.append(s.charAt(i));
            } else {
                right.append(s.charAt(i));
            }
        }

        return right.append(left).toString();
    }
}
