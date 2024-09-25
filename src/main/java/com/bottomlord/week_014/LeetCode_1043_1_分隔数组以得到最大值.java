package com.bottomlord.week_014;

public class LeetCode_1043_1_分隔数组以得到最大值 {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int len = A.length;
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            int curMax = A[i];
            for (int k = 1; k <= K && i - k + 1 >= 0; k++) {
                curMax = Math.max(curMax, A[i - k + 1]);
                dp[i] = Math.max(dp[i], (i >= k ? dp[i - k] : 0) + k * curMax);
            }
        }

        return dp[len - 1];
    }
}
