package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/27 16:39
 */
public class LeetCode_813_1_最大平均值和分组 {
    public double largestSumOfAverages(int[] A, int K) {
        int len = A.length;
        int[] sum = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                sum[i] = A[i];
            } else {
                sum[i] = sum[i - 1] + A[i];
            }
        }

        double[][] dp = new double[len][K + 1];
        for (int i = 0; i < len; i++) {
            dp[i][0] = 1.0 * sum[i] / i;
            for (int k = 2; k <= K && k <= i; k++) {
                for (int j = 1; j < i; j++) {
                    dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + 1.0 * (sum[i] - sum[j]) / (i - j));
                }
            }
        }

        return dp[len][K];
    }
}
