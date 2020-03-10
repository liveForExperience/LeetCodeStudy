package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/10 8:21
 */
public class Interview_60_1_n个色子的点数 {
    public double[] twoSum(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int s = i; s <= 6 * n; s++) {
                for (int k = 1; k <= 6; k++) {
                    if (s - k >= i - 1) {
                        dp[i][s] += dp[i - 1][s - k];
                    }
                }
            }
        }

        double total = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];

        for (int i = n; i <= 6 * n; i++) {
            ans[i - n] = dp[n][i] / total;
        }
        return ans;
    }
}
