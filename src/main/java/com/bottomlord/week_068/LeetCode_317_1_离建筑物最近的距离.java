package com.bottomlord.week_068;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2020/10/28 8:21
 */
public class LeetCode_317_1_离建筑物最近的距离 {
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int shortestDistance(int[][] grid) {
        int row = grid.length;
        if (row == 0) {
            return 0;
        }

        int col = grid[0].length;
        int[][] totalDistance = new int[row][col];

        int mark = 0, ans = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    ans = bfs(grid, row, col, i, j, mark, totalDistance);
                    mark--;
                }
            }
        }

        return ans;
    }

    private int bfs(int[][] grid, int row, int col, int r, int c, int mark, int[][] totalDistance) {
        int ans = Integer.MAX_VALUE;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c, 0});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (arr == null) {
                continue;
            }

            int curDistance = arr[2];

            for (int i = 0; i < directions.length; i++) {
                int nextRow = arr[0] + directions[i][0],
                    nextCol = arr[1] + directions[i][1];

                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && grid[nextRow][nextCol] == mark) {
                    int nextDistance = curDistance + 1;
                    totalDistance[nextRow][nextCol] += nextDistance;
                    ans = Math.min(ans, totalDistance[nextRow][nextCol]);

                    queue.offer(new int[]{nextRow, nextCol, nextDistance});
                    grid[nextRow][nextCol]--;
                }
            }
        }

        return ans;
    }
}
