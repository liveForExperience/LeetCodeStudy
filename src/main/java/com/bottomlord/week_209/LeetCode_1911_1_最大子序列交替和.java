package com.bottomlord.week_209;

/**
 * @author chen yue
 * @date 2023-07-11 22:39:30
 */
public class LeetCode_1911_1_最大子序列交替和 {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n + 1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - nums[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i - 1]);
        }

        return Math.max(dp[n][0], dp[n][1]);
    }
}
