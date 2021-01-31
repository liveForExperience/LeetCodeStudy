package com.bottomlord.week_081;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeetCode_778_1_水位上升的游泳池中游泳 {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int swimInWater(int[][] grid) {
        int n = grid.length, start = grid[0][0], end = n * n - 1, ans = n * n - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{0, 0});

            boolean[] memo = new boolean[n * n];
            memo[0] = true;

            while (!queue.isEmpty()) {
                int[] arr = queue.poll();

                for (int[] direction : directions) {
                    int r = arr[0] + direction[0], c = arr[1] + direction[1];

                    if (r >= 0 && r < n && c >= 0 && c < n && !memo[n * r + c] && grid[r][c] <= mid) {
                        queue.offer(new int[]{r, c});
                        memo[r * n + c] = true;
                    }
                }
            }

            if (memo[n * n - 1]) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }

        return ans;
    }
}
