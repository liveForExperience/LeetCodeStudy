package com.bottomlord.week_081;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeetCode_1631_1_最小体力消耗路径 {
    private int[][] directions = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};

    public int minimumEffortPath(int[][] heights) {
        int start = 0, end = 999999, ans = 0, row = heights.length, col = heights[0].length;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{0, 0});

            boolean[] memo = new boolean[row * col];
            memo[0] = true;

            while (!queue.isEmpty()) {
                int[] height = queue.poll();

                for (int i = 0; i < directions.length; i++) {
                    int r = height[0] + directions[i][0], c = height[1] + directions[i][1];
                    if (r >= 0 && r < row && c >= 0 && c < col && !memo[r * col + c] && Math.abs(heights[r][c] - heights[height[0]][height[1]]) <= mid) {
                        queue.offer(new int[]{r, c});
                        memo[r * col + c] = true;
                    }
                }
            }

            if (memo[row * col - 1]) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }

        return ans;
    }
}
