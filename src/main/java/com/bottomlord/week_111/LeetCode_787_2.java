package com.bottomlord.week_111;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-08-24 08:34:16
 */
public class LeetCode_787_2 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INF = Integer.MAX_VALUE;
        int[][] dp = new int[k + 2][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, INF);
        }

        dp[0][src] = 0;

        for (int t = 1; t <= k + 1; t++) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                dp[t][i] = Math.min(dp[t][i], dp[t - 1][j] + cost);
            }
        }

        int min = INF;
        for (int i = 1; i <= k + 1; i++) {
            min = Math.min(min, dp[i][dst]);
        }

        return min == INF ? -1 : min;
    }
}
