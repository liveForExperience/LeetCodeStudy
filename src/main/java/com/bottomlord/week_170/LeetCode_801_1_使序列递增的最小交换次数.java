package com.bottomlord.week_170;

/**
 * @author chen yue
 * @date 2022-10-10 19:28:05
 */
public class LeetCode_801_1_使序列递增的最小交换次数 {
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[n][2];
        dp[0][1] = 1;

        for (int i = 1; i < n; i++) {
            int a1 = nums1[i], a2 = nums1[i - 1],
                b1 = nums2[i], b2 = nums2[i - 1];

            if ((a1 > a2 && b1 > b2) && (a1 > b2 && b1 > a2)) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
            } else if (a1 > a2 && b1 > b2) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }

        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }
}
