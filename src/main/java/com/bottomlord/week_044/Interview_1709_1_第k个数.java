package com.bottomlord.week_044;

/**
 * @author ChenYue
 * @date 2020/5/7 8:06
 */
public class Interview_1709_1_第k个数 {
    public int getKthMagicNumber(int k) {
        int p3 = 0, p5 = 0, p7 = 0;
        int[] dp = new int[k];
        dp[0] = 1;
        for (int i = 1; i < k; i++) {
            dp[i] = Math.min(dp[p3] * 3, Math.min(dp[p5] * 5, dp[p7] * 7));

            if (dp[p3] * 3 == dp[i]) {
                p3++;
            }

            if (dp[p5] * 5 == dp[i]) {
                p5++;
            }

            if (dp[p7] * 7 == dp[i]) {
                p7++;
            }
        }

        return dp[k - 1];
    }
}
