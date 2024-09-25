package com.bottomlord.week_229;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-11-27 18:53:33
 */
public class LeetCode_907_1_子数组的最小值之和 {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length, mod = 1000000007, ans = Arrays.stream(arr).sum() % mod;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], arr[j]);
                ans += dp[i][j];
                ans %= mod;
            }
        }

        return ans;
    }
}
