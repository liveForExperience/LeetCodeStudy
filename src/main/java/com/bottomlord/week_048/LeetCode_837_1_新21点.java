package com.bottomlord.week_048;

/**
 * @author ChenYue
 * @date 2020/6/3 8:29
 */
public class LeetCode_837_1_新21点 {
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[K + W + 1];
        for (int i = K; i < K + W && i <= N; i++) {
            dp[i] = 1.0;
        }

        dp[K - 1] = 1.0 * Math.min(W, N - K + 1) / W;
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = (dp[i + 1] - dp[i + W + 1]) / W + dp[i + 1];
        }
        return dp[0];
    }
}
