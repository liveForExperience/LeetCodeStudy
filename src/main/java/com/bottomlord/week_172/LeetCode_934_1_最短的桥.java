package com.bottomlord.week_172;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-10-29 10:33:23
 */
public class LeetCode_934_1_最短的桥 {
    private final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestBridge(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 || grid[i][j] == -1) {
                    continue;
                }

                dfs(grid, i, j, queue);

                int ans = 0;
                while (!queue.isEmpty()) {
                    int count = queue.size();

                    while (count-- > 0) {
                        int[] arr = queue.poll();
                        if (arr == null) {
                            continue;
                        }

                        int x = arr[0], y = arr[1];
                        for (int[] direction : directions) {
                            int nx = x + direction[0], ny = y + direction[1];
                            if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid[0].length || grid[nx][ny] == -1) {
                                continue;
                            }

                            if (grid[nx][ny] == 1) {
                                return ans;
                            }

                            queue.offer(new int[]{nx, ny});
                            grid[nx][ny] = -1;
                        }
                    }

                    ans++;
                }
            }
        }

        return 0;
    }

    private void dfs(int[][] grid, int x, int y, Queue<int[]> queue) {
        int r = grid.length, c = grid[0].length;
        if (x < 0 || x >= r || y < 0 || y >= c || grid[x][y] == -1 || grid[x][y] == 0) {
            return;
        }

        queue.offer(new int[]{x, y});
        grid[x][y] = -1;

        for (int[] direction : directions) {
            dfs(grid, x + direction[0], y + direction[1], queue);
        }
    }
}
