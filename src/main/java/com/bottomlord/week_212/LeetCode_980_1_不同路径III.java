package com.bottomlord.week_212;

/**
 * @author chen yue
 * @date 2023-08-05 15:53:57
 */
public class LeetCode_980_1_不同路径III {
    private final int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private boolean[][] memo;
    private int ans, r, c, target = 0, cnt = 0;
    public int uniquePathsIII(int[][] grid) {
        this.ans = 0;
        this.grid = grid;
        this.r = grid.length;
        this.c = grid[0].length;
        this.memo = new boolean[r][c];
        int startX = -1, startY = -1;

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (grid[x][y] == 1) {
                    startX = x;
                    startY = y;
                } else if (grid[x][y] == 0) {
                    target++;
                }
            }
        }

        backTrack(startX, startY);

        return ans;
    }

    private void backTrack(int x, int y) {
        if (!isValid(x, y)) {
            return;
        }

        if (grid[x][y] == 0) {
            cnt++;
        }

        if (grid[x][y] == 2) {
            if (cnt == target) {
                ans++;
            }
            return;
        }

        memo[x][y] = true;

        for (int[] direction : directions) {
            int nx = direction[0], ny = direction[1];
            backTrack(x + nx, y + ny);
        }

        memo[x][y] = false;

        if (grid[x][y] == 0) {
            cnt--;
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c && grid[x][y] != -1 && !memo[x][y];
    }
}
