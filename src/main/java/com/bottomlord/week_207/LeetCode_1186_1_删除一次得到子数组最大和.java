package com.bottomlord.week_207;

/**
 * @author chen yue
 * @date 2023-06-27 22:05:25
 */
public class LeetCode_1186_1_删除一次得到子数组最大和 {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        int max = dp[0][0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            max = Math.max(dp[i][0], dp[i][1]);
        }
        return max;
    }
}
