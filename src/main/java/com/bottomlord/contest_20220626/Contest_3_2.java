package com.bottomlord.contest_20220626;

/**
 * @author chen yue
 * @date 2022-06-27 16:46:37
 */
public class Contest_3_2 {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        return Math.max(solve(nums1, nums2), solve(nums2, nums1));
    }

    private int solve(int[] nums1, int[] nums2) {
        int n = nums1.length, sum = 0, max = 0;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + (nums2[i - 1] - nums1[i - 1]), 0);
            max = Math.max(max, dp[i]);
            sum += nums1[i - 1];
        }
        return sum + max;
    }
}
