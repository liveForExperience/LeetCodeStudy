package com.bottomlord.week_095;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/5/6 11:02
 */
public class LeetCode_1473_2 {
    private static final int MAX = Integer.MAX_VALUE / 2;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        for (int i = 0; i < houses.length; i++) {
            houses[i]--;
        }
        int[][][] dp = new int[m][n][target];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], MAX);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (houses[i] != -1 && houses[i] != j) {
                    continue;
                }

                for (int k = 0; k <  target; k++) {
                    for (int j1 = 0; j1 < n; j1++) {
                        if (j == j1) {
                            if (i == 0) {
                                if (k == 0) {
                                    if (houses[i] == -1) {
                                        dp[i][j][k] = cost[i][j];
                                    } else {
                                        dp[i][j][k] = 0;
                                    }
                                }
                            } else {
                                if (houses[i] == -1) {
                                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k] + cost[i][j]);
                                } else {
                                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k]);
                                }
                            }
                        } else {
                            if (i > 0 && k > 0) {
                                if (houses[i] == -1) {
                                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j1][k - 1] + cost[i][j]);
                                } else {
                                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j1][k - 1]);
                                }
                            }
                        }
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i][target - 1]);
        }
        return ans;
    }
}
