package com.bottomlord.week_100;

/**
 * @author ChenYue
 * @date 2021/6/9 8:16
 */
public class LeetCode_879_1_盈利计划 {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int g = group.length, mod = 1000000007;
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;

        for (int i = 0; i < g; i++) {
            int gCost = group[i], pGet = profit[i];
            for (int j = n; j >= gCost; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] += dp[j - gCost][Math.max(k - pGet, 0)];
                    if (dp[j][k] > mod) {
                        dp[j][k] -= mod;
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i <= n; i++) {
                sum += dp[i][minProfit];
                if (sum > mod) {
                    sum -= mod;
                }
        }

        return sum;
    }
}
