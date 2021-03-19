package com.bottomlord.week_088;

/**
 * @author ChenYue
 * @date 2021/3/19 9:05
 */
public class LeetCode_494_2 {
    public int findTargetSumWays(int[] nums, int S) {
        if (S > 1000) {
            return 0;
        }

        int len = nums.length;
        int[][] dp = new int[len][2001];

        dp[0][nums[0] + 1000]++;
        dp[0][-nums[0] + 1000]++;

        for (int i = 1; i < len; i++) {
            for (int j = -1000; j <= 1000; j++) {
                if (dp[i - 1][j + 1000] > 0) {
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000];
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000];
                }
            }
        }

        return dp[len - 1][S];
    }
}
