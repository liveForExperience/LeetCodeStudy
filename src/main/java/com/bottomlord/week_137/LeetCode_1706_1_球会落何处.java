package com.bottomlord.week_137;

/**
 * @author chen yue
 * @date 2022-02-24 08:53:00
 */
public class LeetCode_1706_1_球会落何处 {
    public int[] findBall(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[] ans = new int[c];
        for (int i = 0; i < c; i++) {
            ans[i] = dfs(0, i, r, c, grid, true, -1, 0);
        }

        return ans;
    }

    private int dfs(int x, int y, int r, int c, int[][] grid, boolean upper, int preDirIndex, int pre) {
        if (y < 0 || y >= c) {
            return -1;
        }

        int direction = grid[x][y];
        if (preDirIndex != 2 && pre + direction == 0) {
            return -1;
        }

        if (x == r - 1 && !upper) {
            return y;
        }

        if (direction == 1) {
            if (upper) {
                return dfs(x, y + 1, r, c, grid, false, 1, direction);
            } else {
                return dfs(x + 1, y, r, c, grid, true, 2, direction);
            }
        } else {
            if (upper) {
                return dfs(x, y - 1, r, c, grid, false, 0, direction);
            } else {
                return dfs(x + 1, y, r, c, grid, true, 2, direction);
            }
        }
    }
}
