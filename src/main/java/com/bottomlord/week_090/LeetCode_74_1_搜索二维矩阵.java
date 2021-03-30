package com.bottomlord.week_090;

/**
 * @author ChenYue
 * @date 2021/3/30 8:22
 */
public class LeetCode_74_1_搜索二维矩阵 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[row - 1][col - 1]) {
            return false;
        }

        return dfs(matrix, 0, 0, row, col, target, new boolean[row][col]);
    }

    private boolean dfs(int[][] matrix, int r, int c, int row, int col, int target, boolean[][] memo) {
        if (r < 0 || r >= row || c < 0 || c >= col || memo[r][c]) {
            return false;
        }

        memo[r][c] = true;
        int pivot = matrix[r][c];
        if (pivot == target) {
            return true;
        }

        if (pivot > target) {
            return dfs(matrix, r, c - 1, row, col, target, memo) || dfs(matrix, r - 1, c, row, col, target, memo);
        } else {
            return dfs(matrix, r, c + 1, row, col, target, memo) || dfs(matrix, r + 1, c, row, col, target, memo);
        }
    }
}
