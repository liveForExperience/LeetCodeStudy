package com.bottomlord.week_025;

public class LeetCode_486_3 {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];

        System.arraycopy(nums, 0, dp, 0, len);

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[i]);
            }
        }

        return dp[0] >= 0;
    }
}