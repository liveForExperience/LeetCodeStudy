package com.bottomlord.week_202;

/**
 * @author chen yue
 * @date 2023-05-26 22:10:08
 */
public class LeetCode_1091_1_二进制矩阵中的最短路径 {
    private int n;
    private boolean[][] memo;
    private int[][] grid;
    private int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        this.n = grid.length;
        this.memo = new boolean[n][n];
        this.grid = grid;
        memo[0][0] = true;
        return backTrack(0, 0);
    }

    private int backTrack(int x, int y) {
        if (x == n - 1 && y == n - 1) {
            return 1;
        }

        int ans = Integer.MAX_VALUE;
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (!isValid(nx, ny)) {
                continue;
            }

            memo[nx][ny] = true;
            int path = backTrack(nx, ny);
            memo[nx][ny] = false;

            if (path == -1) {
                continue;
            }

            ans = Math.min(path, ans);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans + 1;
    }

    private boolean isValid(int x, int y) {
        return in(x, y) && !memo[x][y] && grid[x][y] == 0;
    }

    private boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
