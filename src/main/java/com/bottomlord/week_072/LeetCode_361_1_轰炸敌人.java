package com.bottomlord.week_072;

/**
 * @author ChenYue
 * @date 2020/11/27 8:57
 */
public class LeetCode_361_1_轰炸敌人 {
    private int row, col;
    private char[][] grid;

    public int maxKilledEnemies(char[][] grid) {
        this.grid = grid;
        row = grid.length;
        if (row == 0) {
            return 0;
        }

        col = grid[0].length;
        if (col == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    int count = 0;
                    count += recurse(i + 1, j, new int[]{1, 0});
                    count += recurse(i - 1, j, new int[]{-1, 0});
                    count += recurse(i, j + 1, new int[]{0, 1});
                    count += recurse(i, j - 1, new int[]{0, -1});
                    max = Math.max(max, count);
                }
            }
        }

        return max;
    }

    private int recurse(int x, int y, int[] direction) {
        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 'W') {
            return 0;
        }

        return (grid[x][y] == 'E' ? 1 : 0) + recurse(x + direction[0], y + direction[1], direction);
    }
}
