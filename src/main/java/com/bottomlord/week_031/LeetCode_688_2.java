package com.bottomlord.week_031;

/**
 * @author ThinkPad
 * @date 2020/2/4 15:04
 */
public class LeetCode_688_2 {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] paths = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        double[][][] memo = new double[K + 1][N][N];
        return dfs(N, K, r, c, memo, paths);
    }

    private double dfs(int n, int k, int r, int c, double[][][] memo, int[][] paths) {
        if (!isValid(n, r, c)) {
            return 0.0;
        }

        if (k == 0) {
            return 1.0;
        }

        if (memo[k][r][c] != 0.0) {
            return memo[k][r][c];
        }

        double p = 0.0;
        for (int[] path : paths) {
            p += dfs(n, k - 1, r + path[0], c + path[1], memo, paths);
        }

        p /= 8;
        memo[k][r][c] = p;
        return p;
    }

    private boolean isValid(int n, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}