package com.bottomlord.week_029;

/**
 * @author ThinkPad
 * @date 2020/1/25 13:39
 */
public class LeetCode_518_2 {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        if (len == 0) {
            return amount == 0 ? 1 : 0;
        }

        int[][] dp = new int[len][amount + 1];
        for (int i = coins[0]; i <= amount; i += coins[0]) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }

        return dp[len - 1][amount];
    }
}