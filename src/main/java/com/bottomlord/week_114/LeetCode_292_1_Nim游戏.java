package com.bottomlord.week_114;

/**
 * @author chen yue
 * @date 2021-09-18 08:54:24
 */
public class LeetCode_292_1_Nim游戏 {
    public boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }

        boolean[] dp = new boolean[n + 1];
        dp[0] = dp[1] = dp[2] = dp[3] = true;
        for (int i = 4; i <= n; i++) {
            dp[i] = !(dp[i - 1] | dp[i - 2] | dp[i - 3]);
        }

        return dp[n];
    }
}
