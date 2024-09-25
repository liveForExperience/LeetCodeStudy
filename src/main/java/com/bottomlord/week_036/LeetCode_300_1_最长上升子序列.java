package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/14 11:50
 */
public class LeetCode_300_1_最长上升子序列 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = 0, len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 0; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
