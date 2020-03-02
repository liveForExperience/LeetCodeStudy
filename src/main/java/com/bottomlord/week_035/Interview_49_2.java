package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/2 13:13
 */
public class Interview_49_2 {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i = 0, i2 = 0, i3 = 0, i5 = 0;

        while (++i < n) {
            int min = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            dp[i] = min;
            if (min == dp[i2] * 2) {
                i2++;
            }

            if (min == dp[i3] * 3) {
                i3++;
            }

            if (min == dp[i5] * 5) {
                i5++;
            }
        }
        return dp[n - 1];
    }
}