package com.bottomlord.week_109;

/**
 * @author chen yue
 * @date 2021-08-15 14:06:44
 */
public class LeetCode_576_3 {
    private static final int MOD = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int ans = 0;
        int[][] dp = new int[m][n];
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        dp[startRow][startColumn] = 1;

        for (int i = 0; i < maxMove; i++) {
            int[][] newDp = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[j][k];

                    if (count > 0) {
                        for (int[] direction : directions) {
                            int nextR = j + direction[0], nextC = k + direction[1];

                            if (nextR >= 0 && nextR < m && nextC >= 0 && nextC < n) {
                                newDp[nextR][nextC] = (newDp[nextR][nextC] + count) % MOD;
                            } else {
                                ans = (ans + count) % MOD;
                            }
                        }
                    }
                }
            }

            dp = newDp;
        }

        return ans;
    }
}
