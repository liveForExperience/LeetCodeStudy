package com.bottomlord.week_224;

/**
 * @author chen yue
 * @date 2023-10-24 09:46:57
 */
public class LeetCode_1155_1_掷骰子等于目标和的方法数 {
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= Math.min(k, target); i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int preSum = i - 1; preSum <= target - (n - i + 1); preSum++) {
                for (int curValue = 1; curValue <= k; curValue++) {
                    int total;
                    if ((total = preSum + curValue) > target) {
                        break;
                    }

                    if (dp[i - 1][preSum] == -1) {
                        break;
                    }

                    dp[i][total] += dp[i - 1][preSum];
                    dp[i][total] %= 1000000007;
                }
            }
        }

        return dp[n][target];
    }
}
