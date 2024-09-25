package com.bottomlord.week_144;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-04-12 09:06:06
 */
public class LeetCode_694_1_不同岛屿的数量 {
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int numDistinctIslands(int[][] grid) {

        Set<String> set = new HashSet<>();
        int row = grid.length, col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, row, col, 0, sb);
                set.add(sb.toString());
            }
        }

        return set.size();
    }

    private void dfs(int[][] grid, int x, int y, int row, int col, int dir, StringBuilder sb) {
        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) {
            return;
        }

        grid[x][y] = 0;
        sb.append(dir);
        for (int i = 0; i < dirs.length; i++) {
            dfs(grid, x + dirs[i][0], y + dirs[i][1], row, col, i, sb);
        }
        sb.append(-dir);
    }
}
