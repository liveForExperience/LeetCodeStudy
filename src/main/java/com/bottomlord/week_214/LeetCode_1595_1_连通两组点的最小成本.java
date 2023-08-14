package com.bottomlord.week_214;

import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-06-20 20:39:05
 */
public class LeetCode_1595_1_连通两组点的最小成本 {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size(), inf = 1 << 30;
        int[][] dp = new int[m + 1][1 << n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], inf);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < (1 << n); j++) {
                for (int k = 0; k < n; k++) {
                    if ((j >> k & 1) == 1) {
                        int c = cost.get(i - 1).get(k);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j ^ (1 << k)] + c);
                        dp[i][j] = Math.min(dp[i][j], dp[i][j ^ (1 << k)] + c);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + c);
                    }
                }
            }
        }

        return dp[m][(1 << n) - 1];
    }
}
