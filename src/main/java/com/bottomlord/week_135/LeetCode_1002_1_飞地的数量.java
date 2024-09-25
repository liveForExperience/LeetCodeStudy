package com.bottomlord.week_135;

/**
 * @author chen yue
 * @date 2022-02-12 10:15:30
 */
public class LeetCode_1002_1_飞地的数量 {
    private int row, col;
    private int[][] grid, directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int numEnclaves(int[][] grid) {
        this.grid = grid;
        this.row = grid.length;
        this.col = grid[0].length;

        boolean[][] memo = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            shrink(i, 0);
            shrink(i, col - 1);
        }

        for (int i = 0; i < col; i++) {
            shrink(0, i);
            shrink(row - 1, i);
        }

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private void shrink(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) {
            return;
        }

        grid[x][y] = 0;

        for (int[] direction : directions) {
            shrink(x + direction[0], y + direction[1]);
        }
    }
}
