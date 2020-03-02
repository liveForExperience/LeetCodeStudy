package com.bottomlord.week_035;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/3/2 8:20
 */
public class Interview_47_1_礼物的最大值 {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        return dfs(grid, 0, 0, grid.length, grid[0].length, new HashMap<>());
    }

    private int dfs(int[][] grid, int x, int y, int row, int col, Map<Integer, Integer> visited) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return 0;
        }

        int right = visited.containsKey(1000 * x + y + 1) ? visited.get(1000 * x + y + 1) : dfs(grid, x, y + 1, row, col, visited);
        int down = visited.containsKey(1000 * (x + 1) + y) ? visited.get(1000 * (x + 1) + y) : dfs(grid, x + 1, y, row, col, visited);

        int max = grid[x][y] + Math.max(right, down);
        visited.put(1000 * x + y, max);
        return max;
    }
}
