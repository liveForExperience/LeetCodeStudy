package com.bottomlord.week_007;

public class LeetCode_459_2 {
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i <= s.length() / 2; i++) {
            if (s.length() % i != 0) {
                continue;
            }
            int j = i;
            for (; j < s.length() && s.charAt(j) == s.charAt(j % i); j++);
            if (s.length() == j) {
                return true;
            }
        }

        return false;
    }
}