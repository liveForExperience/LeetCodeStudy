package com.bottomlord.week_062;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/9/10 8:31
 */
public class LeetCode_265_1_粉刷房子II {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }

        int k = costs[0].length;
        int[][] dp = new int[n][k];
        System.arraycopy(costs[0], 0, dp[0], 0, k);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++) {
                    if (m == j) {
                        continue;
                    }

                    min = Math.min(min, dp[i][m]);
                }

                dp[i][j] = costs[i][j] + min;
            }
        }

        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }
}
