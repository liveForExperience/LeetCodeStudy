package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/27 21:42
 */
public class LeetCode_813_2 {
    public double largestSumOfAverages(int[] A, int K) {
        int len = A.length;
        double[] sum = new double[len + 1];
        for (int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + A[i];
        }

        double[] dp = new double[len];
        for (int i = 0; i < len; i++) {
            dp[i] = (sum[len] - sum[i]) / (len - i);
        }

        for (int k = 0; k < K - 1; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    dp[i] = Math.max(dp[i], (sum[j] - sum[i]) / (j - i) + dp[j]);
                }
            }
        }

        return dp[0];
    }
}