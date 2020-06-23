package com.bottomlord.week_051;

/**
 * @author ChenYue
 * @date 2020/6/23 8:32
 */
public class LeetCode_63_2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] memo = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                memo[i][j] = -1;
            }
        }

        return recurse(obstacleGrid, 0, 0, row, col, memo);
    }

    private int recurse(int[][] board, int r, int c, int row, int col, int[][] memo) {
        int count = 0;
        if (isValid(board, r, c, row, col)) {
            if (memo[r][c] != -1) {
                return memo[r][c];
            }

            if (r == row - 1 && c == col - 1) {
                return 1;
            }

            count = recurse(board, r + 1, c, row, col, memo) + recurse(board, r, c + 1, row, col, memo);
        }

        memo[r][c] = count;
        return count;
    }

    private boolean isValid(int[][] board, int r, int c, int row, int col) {
        return r < row && c < col && board[r][c] != 1;
    }
}
