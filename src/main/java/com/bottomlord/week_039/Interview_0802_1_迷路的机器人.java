package com.bottomlord.week_039;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/3/30 8:11
 */
public class Interview_0802_1_迷路的机器人 {
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return Collections.emptyList();
        }

        LinkedList<List<Integer>> paths = new LinkedList<>();
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        backTrack(0, 0, row, col, obstacleGrid, new boolean[row][col], paths);
        return paths;
    }

    private boolean backTrack(int x, int y, int row, int col, int[][] grid, boolean[][] visited, LinkedList<List<Integer>> paths) {
        if (x >= row || y >= col || grid[x][y] == 1 || visited[x][y]) {
            return false;
        }

        paths.add(Arrays.asList(x, y));
        visited[x][y] = true;

        if (x == row - 1 && y == col - 1) {
            return true;
        }

        if (backTrack(x, y + 1, row, col, grid, visited, paths) ||
            backTrack(x + 1, y, row, col, grid, visited, paths)) {
            return true;
        }

        paths.removeLast();
        return false;
    }
}
