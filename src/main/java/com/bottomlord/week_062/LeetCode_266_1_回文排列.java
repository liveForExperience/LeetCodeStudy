package com.bottomlord.week_062;

/**
 * @author ChenYue
 * @date 2020/9/12 22:51
 */
public class LeetCode_266_1_回文排列 {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int len = 0;
        for (char c : s.toCharArray()) {
            len = Math.max(len, c);
        }

        int[] bucket = new int[len + 1];
        for (char c : s.toCharArray()) {
            bucket[c]++;
        }

        int odd = 0;
        for (int num : bucket) {
            if (num % 2 == 1) {
                odd++;
            }
        }

        return odd <= 1;
    }
}
