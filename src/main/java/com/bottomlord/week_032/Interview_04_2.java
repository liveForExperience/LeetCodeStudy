package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/13 20:30
 */
public class Interview_04_2 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        return dfs(matrix, matrix.length, matrix[0].length, 0, 0, target);
    }

    private boolean dfs(int[][] matrix, int row, int col, int x, int y, int target) {
        if (x >= row || y >= col || matrix[x][y] > target) {
            return false;
        }

        return dfs(matrix, row, col, x + 1, y, target) || dfs(matrix, row, col, x, y + 1, target);
    }
}