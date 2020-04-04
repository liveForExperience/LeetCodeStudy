package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/4 22:55
 */
public class Interview_1009_1_排序矩阵查找 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length, col = matrix[0].length;
        return dfs(matrix, 0, 0, matrix.length, matrix[0].length, target, new boolean[row][col]);
    }

    private boolean dfs(int[][] matrix, int x, int y, int row, int col, int target, boolean[][] memo) {
        if (x >= row || y >= col || target < matrix[x][y] || memo[x][y]) {
            return false;
        }

        if (matrix[x][y] == target) {
            return true;
        }

        memo[x][y] = true;

        return dfs(matrix, x + 1, y, row, col, target, memo) ||
                dfs(matrix, x, y + 1, row, col, target, memo);
    }
}
