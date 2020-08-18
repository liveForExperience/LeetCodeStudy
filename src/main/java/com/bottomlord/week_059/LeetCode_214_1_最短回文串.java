package com.bottomlord.week_059;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/8/18 8:37
 */
public class LeetCode_214_1_最短回文串 {
    public String shortestPalindrome(String s) {
        int len = s.length();
        String rev = reserve(s);

        for (int i = 0; i < len; i++) {
            if (Objects.equals(s.substring(0, len - i), rev.substring(i))) {
                return rev.substring(0, i) + s;
            }
        }

        return "";
    }

    private String reserve(String s) {
        int head = 0, tail = s.length() - 1;
        char[] cs = s.toCharArray();
        while (head < tail) {
            char c = cs[head];
            cs[head] = cs[tail];
            cs[tail] = c;

            head++;
            tail--;
        }

        return new String(cs);
    }
}
