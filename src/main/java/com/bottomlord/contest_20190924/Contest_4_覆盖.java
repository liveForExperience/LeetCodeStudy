package com.bottomlord.contest_20190924;

public class Contest_4_覆盖 {
    public int domino(int n, int m, int[][] broken) {
        if (broken.length == 0) {
            return n * m / 2;
        }

        int[][] grid = new int[n][m];
        for (int[] b : broken) {
            grid[b[0]][b[1]] = 2;
        }

        return backTrack(grid, 0, 0, 0);
    }

    private int backTrack(int[][] grid, int r, int c, int count) {
        if (r >= grid.length) {
            return count;
        }

        if (c >= grid[r].length) {
            return backTrack(grid, r + 1, 0, count);
        }

        if (grid[r][c] != 0) {
            return backTrack(grid, r, c + 1, count);
        }

        int count1 = 0;
        if (c + 1 < grid[r].length && grid[r][c + 1] == 0) {
            grid[r][c] = grid[r][c + 1] = 1;
            count1 = backTrack(grid, r, c + 2, count + 1);
            grid[r][c] = grid[r][c + 1] = 0;
        }

        int count2 = 0;
        if (r + 1 < grid.length && grid[r + 1][c] == 0) {
            grid[r][c] = grid[r + 1][c] = 1;
            count2 = backTrack(grid, r, c + 1, count + 1);
            grid[r][c] = grid[r + 1][c] = 0;
        }

        int count3 = 0;
        if (count1 == 0 && count2 == 0) {
            count3 = backTrack(grid, r, c + 1, count);
        }

        return Math.max(count3, Math.max(count1, count2));
    }
}
