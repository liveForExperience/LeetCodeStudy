package com.bottomlord.week_035;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/8 12:25
 */
public class LeetCode_322_2 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount];
    }
}