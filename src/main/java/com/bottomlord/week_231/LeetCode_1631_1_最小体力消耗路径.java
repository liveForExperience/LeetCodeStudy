package com.bottomlord.week_231;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2023-12-11 09:53:18
 */
public class LeetCode_1631_1_最小体力消耗路径 {
    private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int minimumEffortPath(int[][] heights) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int[] height : heights) {
            for (int cur : height) {
                min = Math.min(cur, min);
                max = Math.max(cur, max);
            }
        }

        int head = 0, tail = max - min, ans = max - min;

        while (head <= tail) {
            int limit = head + (tail - head) / 2;

            if (bfs(heights, limit)) {
                ans = limit;
                tail = limit - 1;
            } else {
                head = limit + 1;
            }
        }

        return ans;
    }

    private boolean bfs(int[][] heights, int limit) {
        int r = heights.length, c = heights[0].length;
        boolean[][] memo = new boolean[r][c];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        memo[0][0] = true;
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int x = arr[0], y = arr[1];

                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (!valid(heights, nx, ny, x, y, limit, memo)) {
                        continue;
                    }

                    memo[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return memo[r - 1][c - 1];
    }

    private boolean valid(int[][] heights, int x, int y, int px, int py, int limit, boolean[][] memo) {
        return x >= 0 && x < heights.length && y >= 0 && y < heights[0].length && !memo[x][y] && Math.abs(heights[x][y] - heights[px][py]) <= limit;
    }
}
