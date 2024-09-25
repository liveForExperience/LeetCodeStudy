package com.bottomlord.week_059;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/8/29 11:28
 */
public class LeetCode_214_2 {
    public String shortestPalindrome(String s) {
        int len = s.length();
        int head = 0;
        for (int tail = len - 1; tail >= 0; tail--) {
            if (Objects.equals(s.charAt(head), s.charAt(tail))) {
                head++;
            }
        }

        if (head == len) {
            return s;
        }

        StringBuilder sb = new StringBuilder(s.substring(head));
        sb.reverse();
        return sb.append(shortestPalindrome(s.substring(0, head))).append(s.substring(head)).toString();
    }
}
