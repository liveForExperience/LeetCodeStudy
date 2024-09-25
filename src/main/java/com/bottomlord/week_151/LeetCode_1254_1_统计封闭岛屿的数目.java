package com.bottomlord.week_151;

/**
 * @author chen yue
 * @date 2022-06-01 15:08:07
 */
public class LeetCode_1254_1_统计封闭岛屿的数目 {
    private int[][] grid;
    private int r, c;
    private final int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public int closedIsland(int[][] grid) {
        this.r = grid.length;
        this.c = grid[0].length;
        this.grid = grid;
        int ans = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }

                if (dfs(i, j)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean dfs(int x, int y) {
        if ((x == 0 || x == r - 1 || y == 0 || y == c - 1) && grid[x][y] == 0) {
            return false;
        }

        grid[x][y] = 1;
        boolean ans = true;
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx < 0 || nx >= r || ny < 0 || ny >= c || grid[nx][ny] == 1) {
                continue;
            }

            ans &= dfs(nx, ny);
        }

        return ans;
    }
}
