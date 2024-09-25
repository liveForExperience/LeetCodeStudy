package com.bottomlord.contest_20220612;

/**
 * @author chen yue
 * @date 2022-06-12 10:30:31
 */
public class Contest_2_1_网格中的最小路径代价 {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = grid[i][j];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                int[] row = grid[i - 1];
                for (int k = 0; k < row.length; k++) {
                    int num = row[k];
                    int cost = moveCost[num][j];
                    min = Math.min(min, dp[i - 1][k] + cost);
                }

                dp[i][j] += min;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i]);
        }

        return ans;
    }
}
