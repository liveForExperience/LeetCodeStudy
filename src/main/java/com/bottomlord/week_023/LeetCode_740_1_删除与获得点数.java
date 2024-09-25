package com.bottomlord.week_023;

public class LeetCode_740_1_删除与获得点数 {
    public int deleteAndEarn(int[] nums) {
        int[] bucket = new int[10001];
        for (int num : nums) {
            bucket[num]++;
        }

        int[][] dp = new int[10001][2];

        for (int i = 1; i < dp.length; i++) {
            int max = Math.max(dp[i - 1][1], dp[i - 1][0]);
            if (bucket[i - 1] > 0) {
                dp[i][1] = dp[i - 1][0] + bucket[i] * i;
            } else {
                dp[i][1] = max + bucket[i] * i;
            }
            dp[i][0] = max;
        }

        return Math.max(dp[10000][0], dp[10000][1]);
    }
}
