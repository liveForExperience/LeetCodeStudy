package com.bottomlord.week_100;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/6/7 8:40
 */
public class LeetCode_494_1_目标和 {
    public int findTargetSumWays(int[] nums, int S) {
        int len = nums.length, sum = Arrays.stream(nums).sum();
        if (sum < S) {
            return 0;
        }

        int[][] dp = new int[len][2 * sum + 1];
        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][-nums[0] + sum] = 1;
            dp[0][nums[0] + sum] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = -sum; j <= sum; j++) {
                int l = j - nums[i] + sum < 0 ? 0 : dp[i - 1][j - nums[i] + sum],
                    r = j + nums[i] + sum > 2 * sum ? 0 : dp[i - 1][j + nums[i] + sum];
                dp[i][j + sum] = l + r;
            }
        }

        return dp[len - 1][S + sum];
    }
}
