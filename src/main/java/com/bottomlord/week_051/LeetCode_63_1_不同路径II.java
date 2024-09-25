package com.bottomlord.week_051;

/**
 * @author ChenYue
 * @date 2020/6/23 8:32
 */
public class LeetCode_63_1_不同路径II {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        return recurse(obstacleGrid, 0, 0, obstacleGrid.length, obstacleGrid[0].length);
    }

    private int recurse(int[][] board, int r, int c, int row, int col) {
        if (r == row - 1 && c == col - 1) {
            return 1;
        }

        int count = 0;
        if (isValid(board, r, c, row, col)) {
            count = recurse(board, r + 1, c, row, col) + recurse(board, r, c + 1, row, col);
        }
        return count;
    }

    private boolean isValid(int[][] board, int r, int c, int row, int col) {
        return r < row && c < col && board[r][c] != 1;
    }
}
