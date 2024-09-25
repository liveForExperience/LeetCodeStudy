package com.bottomlord.week_007;

public class LeetCode_459_1_重复的子字符串 {
    public boolean repeatedSubstringPattern(String s) {
        if (s.length() <= 1) {
            return false;
        }

        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.length / 2 + 1; i++) {
            if ("".equals(s.replaceAll(sb.append(cs[i]).toString(), "")) && i != s.length() - 1) {
                return true;
            }
        }

        return false;
    }
}
