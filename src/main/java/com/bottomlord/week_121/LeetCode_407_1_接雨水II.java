package com.bottomlord.week_121;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2021-11-03 08:41:17
 */
public class LeetCode_407_1_接雨水II {
    public int trapRainWater(int[][] heightMap) {
        int row = heightMap.length, col = heightMap[0].length;
        boolean[][] memo = new boolean[row][col];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    queue.offer(new int[]{i, j, heightMap[i][j]});
                    memo[i][j] = true;
                }
            }
        }

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int ans = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();

            int r = arr[0], c = arr[1], h = arr[2];
            for (int [] dir : dirs) {
                int newR = r + dir[0], newC = c + dir[1];
                if (newR < 0 || newR >= row || newC < 0 || newC >= col || memo[newR][newC]) {
                    continue;
                }

                int newH = heightMap[newR][newC];
                if (newH < h) {
                    ans += h - newH;
                }

                queue.offer(new int[]{newR, newC, Math.max(newH, h)});
                memo[newR][newC] = true;
            }
        }

        return ans;
    }
}
