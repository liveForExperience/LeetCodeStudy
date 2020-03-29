package com.bottomlord.week_038;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2020/3/29 12:42
 */
public class LeetCode_1162_1_地图分析 {
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int row = grid.length, col = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        if (queue.isEmpty() || queue.size() == row * col) {
            return -1;
        }

        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int ans = -1;
        while (!queue.isEmpty()) {
            ans++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int x = arr[0], y = arr[1];

                for (int[] direction : directions) {
                    int nx = x + direction[0], ny = y + direction[1];
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col && grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return ans;
    }
}
