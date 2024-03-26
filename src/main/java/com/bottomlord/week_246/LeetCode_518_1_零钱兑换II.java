package com.bottomlord.week_246;

/**
 * @author chen yue
 * @date 2024-03-25 18:28:07
 */
public class LeetCode_518_1_零钱兑换II {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
