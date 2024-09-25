package com.bottomlord.week_046;

/**
 * @author ChenYue
 * @date 2020/5/21 8:37
 */
public class LeetCode_5_2 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int len = s.length(), start = 0, end = 0;
        for (int i = 0; i < len; i++) {
            int l = Math.max(expand(i, i, s), expand(i, i + 1, s));
            if (l > end - start) {
                start = i - (l - 1) / 2;
                end = i + l / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expand(int start, int end, String s) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        return end - start - 1;
    }
}