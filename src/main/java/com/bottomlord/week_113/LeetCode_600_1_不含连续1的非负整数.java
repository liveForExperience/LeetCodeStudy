package com.bottomlord.week_113;

/**
 * @author chen yue
 * @date 2021-09-11 14:05:05
 */
public class LeetCode_600_1_不含连续1的非负整数 {
    public int findIntegers(int n) {
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int ans = 0, pre = 0;
        for (int i = 29; i >= 0; i--) {
            int val = 1 << i;
            if ((n & val) != 0) {
                n -= val;
                ans += dp[i + 1];

                if (pre == 1) {
                    break;
                }

                pre = 1;
            } else {
                pre = 0;
            }
        }

        return ans;
    }
}
