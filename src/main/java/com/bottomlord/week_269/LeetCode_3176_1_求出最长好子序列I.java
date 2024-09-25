package com.bottomlord.week_269;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-09-06 08:36:55
 */
public class LeetCode_3176_1_求出最长好子序列I {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int[][] dp = new int[n][k + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            for (int j = 0; j <= k; j++) {
                for (int l = 0; l < i; l++) {
                    int add = (nums[l] != nums[i] ? 1 : 0);
                    if (j - add >= 0 && dp[l][j - add] != -1) {
                        dp[i][j] = Math.max(dp[i][j], dp[l][j - add] + 1);
                    }
                }

                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }
}
