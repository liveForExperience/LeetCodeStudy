package com.bottomlord.week_095;

/**
 * @author ChenYue
 * @date 2021/5/3 15:45
 */
public class LeetCode_562_1_矩阵中最长的连续1线段 {
    public int longestLine(int[][] mat) {
        int ans = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    ans = Math.max(ans, dfs(mat, i, j, 1, 0));
                    ans = Math.max(ans, dfs(mat, i, j, 0, 1));
                    ans = Math.max(ans, dfs(mat, i, j, 1, 1));
                    ans = Math.max(ans, dfs(mat, i, j, 1, -1));
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] mat, int r, int c, int dx, int dy) {
        if (outOfBound(mat, r, c)) {
            return 0;
        }

        return dfs(mat, r + dx, c + dy, dx, dy) + 1;
    }

    private boolean outOfBound(int[][] mat, int r, int c) {
        int row = mat.length, col = mat[0].length;
        return r < 0 || r >= row || c < 0 || c >= col || mat[r][c] == 0;
    }
}
