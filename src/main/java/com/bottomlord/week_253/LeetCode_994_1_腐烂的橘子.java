package com.bottomlord.week_253;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2024-05-13 09:04:57
 */
public class LeetCode_994_1_腐烂的橘子 {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int orangesRotting(int[][] grid) {
        int row = grid.length, col = grid[0].length, left = 0, minute = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    left++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                int[] cur = queue.poll();
                if (cur == null) {
                    continue;
                }

                for (int[] direction : directions) {
                    int nr = cur[0] + direction[0], nc = cur[1] + direction[1];
                    if (outOfBound(nr, row, nc, col) || grid[nr][nc] == 0 || grid[nr][nc] == 2) {
                        continue;
                    }

                    grid[nr][nc] = 2;
                    left--;
                    queue.offer(new int[]{nr, nc});
                }
            }

            if (!queue.isEmpty()) {
                minute++;
            }
        }

        return left == 0 ? -1 : minute;
    }

    private boolean outOfBound(int r, int row, int c, int col) {
        return r < 0 || r >= row || c < 0 || c >= col;
    }
}
