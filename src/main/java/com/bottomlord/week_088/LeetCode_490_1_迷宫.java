package com.bottomlord.week_088;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/3/17 8:25
 */
public class LeetCode_490_1_迷宫 {
    private final int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean result = false;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        if (row == 0) {
            return false;
        }

        int col = maze[0].length;
        if (col == 0) {
            return false;
        }

        Set<String> memo = new HashSet<>();
        for (int i = 0; i < dirs.length; i++) {
            memo.add(initKey(start[0], start[1], i));
            backTrack(maze, start[0], start[1], row, col, destination, i, memo);
        }
        return result;
    }

    private void backTrack(int[][] maze, int x, int y, int row, int col, int[] destination, int dirIndex, Set<String> memo) {
        if (result) {
            return;
        }

        int nextX = x + dirs[dirIndex][0], nextY = y + dirs[dirIndex][1];
        if (x == destination[0] && y == destination[1] && (outOfBound(nextX, nextY, row, col) || maze[nextX][nextY] == 1)) {
            result = true;
            return;
        }

        if (outOfBound(nextX, nextY, row, col) || maze[nextX][nextY] == 1) {
            for (int i = 1; i < dirs.length; i++) {
                int newDirIndex = (dirIndex + i) % dirs.length, newX = x + dirs[newDirIndex][0], newY = y + dirs[newDirIndex][1];
                if (outOfBound(newX, newY, row, col) || maze[newX][newY] == 1 || memo.contains(initKey(x, y, newDirIndex))) {
                    continue;
                }
                memo.add(initKey(x, y, newDirIndex));

                backTrack(maze, newX, newY, row, col, destination, newDirIndex, memo);
                if (result) {
                    return;
                }
            }
        } else {
            backTrack(maze, nextX, nextY, row, col, destination, dirIndex, memo);
        }
    }

    private boolean outOfBound(int x, int y, int row, int col) {
        return x < 0 || x >= row || y < 0 || y >= col;
    }

    private String initKey(int x, int y, int dir) {
        return x + "#" + y + "#" + dir;
    }
}
