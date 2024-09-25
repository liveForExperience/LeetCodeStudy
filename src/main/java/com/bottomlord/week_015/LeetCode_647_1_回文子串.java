package com.bottomlord.week_015;

public class LeetCode_647_1_回文子串 {
    private int sum;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            spread(s, i, i);
            spread(s, i, i + 1);
        }
        return sum;
    }

    private void spread(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            sum++;
            start--;
            end++;
        }
    }
}