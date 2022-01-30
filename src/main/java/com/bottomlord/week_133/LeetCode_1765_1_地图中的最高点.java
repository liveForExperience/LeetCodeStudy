package com.bottomlord.week_133;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-01-29 09:13:08
 */
public class LeetCode_1765_1_地图中的最高点 {
    private int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] highestPeak(int[][] isWater) {
        int row = isWater.length, col = isWater[0].length;
        int[][] matrix = new int[row][col];

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean water = isWater[i][j] == 1;
                if (water) {
                    queue.offer(new int[]{i, j});
                    memo[i][j] = true;
                }
            }
        }

        int level = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int r = arr[0], c = arr[1];

                for (int[] direction : directions) {
                    int newR = r + direction[0], newC = c + direction[1];
                    if (newR < 0 || newR >= row ||
                        newC < 0 || newC >= col ||
                        memo[newR][newC]) {
                        continue;
                    }
                    queue.offer(new int[]{newR, newC});
                    matrix[newR][newC] = level;
                    memo[newR][newC] = true;
                }
            }

            level++;
        }

        return matrix;
    }
}
