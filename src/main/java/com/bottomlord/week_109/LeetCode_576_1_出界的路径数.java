package com.bottomlord.week_109;

/**
 * @author chen yue
 * @date 2021-08-15 00:09:53
 */
public class LeetCode_576_1_出界的路径数 {
    private int mod = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        return dfs(m, n, maxMove, 0, startRow, startColumn) % mod;
    }

    private int dfs(int m, int n, int maxMove, int move, int row, int col) {
        if (move > maxMove) {
            return 0;
        }

        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 1;
        }

        return dfs(m, n, maxMove, move + 1, row + 1, col) %  mod+
                dfs(m, n, maxMove, move + 1, row - 1, col) % mod +
                dfs(m, n, maxMove, move + 1, row, col + 1) % mod +
                dfs(m, n, maxMove, move + 1, row, col - 1) % mod;
    }
}
