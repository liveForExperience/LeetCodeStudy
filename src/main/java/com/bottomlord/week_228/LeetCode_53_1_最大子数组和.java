package com.bottomlord.week_228;

/**
 * @author chen yue
 * @date 2023-11-20 09:41:44
 */
public class LeetCode_53_1_最大子数组和 {
    public int maxSubArray(int[] nums) {
        int n = nums.length, ans = Integer.MIN_VALUE;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = nums[i - 1] + Math.max(0, dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
