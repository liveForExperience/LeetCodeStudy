package com.bottomlord.week_5;

public class LeetCode_409_2 {
    public int longestPalindrome(String s) {
        int[] bucket = new int[128];
        for (char c : s.toCharArray()) {
            bucket[c]++;
        }

        int len = 0;
        for (int num : bucket) {
            len += num / 2 * 2;
            if (num % 2 == 1 && len % 2 == 0) {
                len++;
            }
        }

        return len;
    }
}
