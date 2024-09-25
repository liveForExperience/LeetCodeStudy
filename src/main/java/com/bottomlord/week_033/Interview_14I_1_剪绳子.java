package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/17 13:06
 */
public class Interview_14I_1_剪绳子 {
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
        }

        return dp[n];
    }
}
