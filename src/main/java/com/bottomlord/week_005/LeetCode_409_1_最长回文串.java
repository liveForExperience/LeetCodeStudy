package com.bottomlord.week_005;

public class LeetCode_409_1_最长回文串 {
    public int longestPalindrome(String s) {
        int[] bucket = new int[52];
        for (char c: s.toCharArray()) {
            int index;
            if (c >= 'a') {
                index = c - 'a';
            } else {
                index = c - 'A' + 26;
            }
            bucket[index] += 1;
        }

        boolean odd = false;
        int len = 0;
        for (int num : bucket) {
            if (num == 0) {
                continue;
            }

            if (num % 2 == 0) {
                len += num;
            } else {
                len += num - 1;

                if (!odd) {
                    len++;
                    odd = true;
                }
            }
        }
        return len;
    }
}
