package com.bottomlord.week_202;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2023-05-26 22:55:04
 */
public class LeetCode_1091_2 {
    private int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private int n;
    private boolean[][] memo;
    private int[][] grid;
    public int shortestPathBinaryMatrix(int[][] grid) {
        this.memo = new boolean[n][n];
        this.grid = grid;
        this.n = grid.length;
        if (isNotValid(0, 0)) {
            return -1;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});

        int path = 0;
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                int[] arr = queue.poll();
                int x = arr[0], y = arr[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];

                    if (nx == n - 1 && ny == n - 1) {
                        return path + 1;
                    }

                    if (isNotValid(nx, ny)) {
                        continue;
                    }

                    queue.offer(new int[]{nx, ny});
                    grid[nx][ny] = 1;
                }
            }
            path++;
        }

        return -1;
    }

    private boolean isNotValid(int x, int y) {
        return !in(x, y) || grid[x][y] != 0;
    }

    private boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}