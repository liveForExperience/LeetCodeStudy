package com.bottomlord.week_175;

/**
 * @author chen yue
 * @date 2022-11-21 21:30:38
 */
public class LeetCode_808_1_分汤 {
    public double soupServings(int n) {
        n = Math.min(200, (int)Math.ceil(n / 25.0));
        double[][] dp = new double[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
        }

        dp[0][0] = 0.5;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                double a = dp[Math.max(0, i - 4)][j],
                        b = dp[Math.max(0, i - 3)][Math.max(0, j - 1)],
                        c = dp[Math.max(0, i - 2)][Math.max(0, j - 2)],
                        d = dp[Math.max(0, i - 1)][Math.max(0, j - 3)];
                dp[i][j] = 0.25 * (a + b + c + d);
            }
        }

        return dp[n][n];
    }
}
