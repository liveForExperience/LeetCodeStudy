package com.bottomlord.week_193;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-03-20 18:49:57
 */
public class LeetCode_1012_1_至少有1位重复的数字 {
    private String s;
    private int[][] memo;

    public int numDupDigitsAtMostN(int n) {
        this.s = Integer.toString(n);
        this.memo = new int[s.length()][1 << 10];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return n - count(0, 0, true, false);
    }

    private int count(int index, int mask, boolean isLimit, boolean isNum) {
        if (index == s.length()) {
            return isNum ? 1 : 0;
        }

        if (!isLimit && isNum && memo[index][mask] != -1) {
            return memo[index][mask];
        }

        int ans = 0;
        if (!isNum) {
            ans = count(index + 1, mask, false, false);
        }

        int limit = isLimit ? (s.charAt(index) - '0') : 9;
        for (int i = isNum ? 0 : 1; i <= limit; i++) {
            if ((mask >> i & 1) == 0) {
                ans += count(index + 1, mask | (1 << i), isLimit && i == limit, true);
            }
        }

        if (!isLimit && isNum) {
            memo[index][mask] = ans;
        }

        return ans;
    }
}
