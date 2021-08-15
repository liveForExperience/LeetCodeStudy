package com.bottomlord.week_109;

/**
 * @author chen yue
 * @date 2021-08-15 00:19:15
 */
public class LeetCode_576_2 {
    private int mod = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[maxMove + 1][m][n];
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int ans = 0;
        dp[0][startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int path = dp[i][j][k];

                    if (path > 0) {
                        for (int[] direction : directions) {
                            int nextRow = j + direction[0], nextCol = k + direction[1];

                            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n) {
                                dp[i + 1][nextRow][nextCol] = (dp[i + 1][nextRow][nextCol] + path) % mod;
                            } else {
                                ans = (ans + path) % mod;
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }
}
