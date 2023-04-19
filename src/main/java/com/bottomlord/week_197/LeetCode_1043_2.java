package com.bottomlord.week_197;

/**
 * @author chen yue
 * @date 2023-04-19 22:26:55
 */
public class LeetCode_1043_2 {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int max = arr[i];
            for (int len = 1; len <= k && i - len + 1 >= 0; len++) {
                max = Math.max(max, arr[i - len + 1]);
                dp[i] = Math.max(dp[i], (i - len >= 0 ? dp[i - len] : 0) + len * max);
            }
        }

        return dp[n - 1];
    }
}
