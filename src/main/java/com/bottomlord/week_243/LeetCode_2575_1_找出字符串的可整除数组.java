package com.bottomlord.week_243;

/**
 * @author chen yue
 * @date 2024-03-07 09:30:29
 */
public class LeetCode_2575_1_找出字符串的可整除数组 {
    public int[] divisibilityArray(String word, int m) {
        long mod = 0;
        int n = word.length();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            mod = (mod * 10 + (word.charAt(i) - '0')) % m;
            ans[i] = (mod == 0) ? 1 : 0;
        }

        return ans;
    }
}
