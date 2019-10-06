package com.bottomlord.contest_20191006;

import java.util.ArrayList;
import java.util.Arrays;

public class Contest_3_1_黄金矿工 {
    private int max = 0;
    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int[][] copy = new int[grid.length][grid[i].length];
                for (int k = 0; k < grid.length; k++) {
                    copy[k] = Arrays.copyOf(grid[k], grid[k].length);
                }
                recurse(copy, i, j, 0);
            }
        }

        return max;
    }

    private void recurse(int[][] grid, int r, int c, int count) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[r].length || grid[r][c] == 0) {
            return;
        }

        count += grid[r][c];
        grid[r][c] = 0;
        max = Math.max(max, count);

        int[][] copy1 = new int[grid.length][grid[r].length];
        for (int k = 0; k < grid.length; k++) {
            copy1[k] = Arrays.copyOf(grid[k], grid[k].length);
        }
        recurse(copy1, r - 1, c, count);

        int[][] copy2 = new int[grid.length][grid[r].length];
        for (int k = 0; k < grid.length; k++) {
            copy2[k] = Arrays.copyOf(grid[k], grid[k].length);
        }
        recurse(copy2, r, c + 1, count);

        int[][] copy3 = new int[grid.length][grid[r].length];
        for (int k = 0; k < grid.length; k++) {
            copy3[k] = Arrays.copyOf(grid[k], grid[k].length);
        }
        recurse(copy3, r + 1, c, count);

        int[][] copy4 = new int[grid.length][grid[r].length];
        for (int k = 0; k < grid.length; k++) {
            copy4[k] = Arrays.copyOf(grid[k], grid[k].length);
        }
        recurse(copy4, r, c - 1, count);
    }
}
