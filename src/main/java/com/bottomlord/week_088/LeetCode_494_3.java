package com.bottomlord.week_088;

/**
 * @author ChenYue
 * @date 2021/3/19 12:12
 */
public class LeetCode_494_3 {
    public int findTargetSumWays(int[] nums, int S) {
        if (S > 0) {
            return 0;
        }

        int len = nums.length;
        int[] dp = new int[2001];

        dp[nums[0] + 1000]++;
        dp[-nums[0] + 1000]++;

        for (int i = 1; i < len; i++) {
            int[] next = new int[2001];
            for (int j = -1000; j <= 1000; j++) {
                if (dp[j + 1000] > 0) {
                    next[j - nums[i] + 1000] += dp[j + 1000];
                    next[j + nums[i] + 1000] += dp[j + 1000];
                }
            }
            dp = next;
        }

        return dp[S];
    }
}
