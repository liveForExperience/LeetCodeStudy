package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/2 8:11
 */
public class Interview_0811_1_硬币 {
    public int waysToChange(int n) {
        if (n < 5) {
            return 1;
        }

        if (n == 5) {
            return 2;
        }

        int[][] dp = new int[4][n + 1];
        int[] coins = {1, 5, 10, 25};

        for (int i = 0; i < 4; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j <= n; j++) {
                if (coins[i] > j) {
                    dp[i][j] = dp[i - 1][j] % 1000000007;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - coins[i]]) % 1000000007;
                }
            }
        }

        return dp[3][n];
    }
}
