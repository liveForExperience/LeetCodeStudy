package com.bottomlord.week_055;

/**
 * @author ChenYue
 * @date 2020/7/26 17:22
 */
public class LeetCode_329_1_矩阵中的最长递增路径 {
    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }

        int col = matrix[0].length;
        if (col == 0) {
            return 0;
        }

        int max = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int len = dfs(matrix, row, col, i, j, Integer.MIN_VALUE, new int[row][col]);
                max = Math.max(max, len);
            }
        }

        return max;
    }

    private int dfs(int[][] matrix, int row, int col, int r, int c, int pre, int[][] memo) {
        if (!isValid(matrix, row, col, r, c, pre)) {
            return 0;
        }

        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        int cur = matrix[r][c];

        int up = dfs(matrix, row, col, r - 1, c, cur, memo);
        int down = dfs(matrix, row, col, r + 1, c, cur, memo);
        int left = dfs(matrix, row, col, r, c - 1, cur, memo);
        int right = dfs(matrix, row, col, r, c + 1, cur, memo);

        int max = Math.max(up, Math.max(down, Math.max(left, right)));

        memo[r][c] = max + 1;
        return max + 1;
    }

    private boolean isValid(int[][] matrix, int row, int col, int r, int c, int pre) {
        return r >= 0 && r < row && c >= 0 && c < col && matrix[r][c] > pre;
    }
}
