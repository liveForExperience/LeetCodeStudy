package com.bottomlord.week_026;

/**
 * @author ThinkPad
 * @date 2020/1/3 8:48
 */
public class LeetCode_264_3 {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0, index = 1;

        while (index < n) {
            int num = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            dp[++index] = num;
            if (num == dp[i2] * 2) {
                i2++;
            }

            if (num == dp[i3] * 2) {
                i3++;
            }

            if (num == dp[i5] * 2) {
                i5++;
            }
        }

        return dp[n - 1];
    }
}