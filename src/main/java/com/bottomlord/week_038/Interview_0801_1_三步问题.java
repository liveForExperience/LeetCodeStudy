package com.bottomlord.week_038;


import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/3/29 21:10
 */
public class Interview_0801_1_三步问题 {
    public int waysToStep(int n) {
        if (n < 3) {
            return n;
        }

        if (n == 3) {
            return 4;
        }

        int[] dp = new int[n + 1];

        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
            dp[i] = (dp[i] + dp[i - 3]) % 1000000007;
        }

        return dp[n];
    }
}
