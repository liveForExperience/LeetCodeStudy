package com.bottomlord.week_041;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2020/4/15 12:15
 */
public class LeetCode_542_1_01矩阵 {
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new ArrayDeque<>();
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }

        int[] dx = new int[]{-1, 1, 0 ,0},
              dy = new int[]{0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (arr == null) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int x = arr[0] + dx[i],
                    y = arr[1] + dy[i];

                if (x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] == -1) {
                    matrix[x][y] = matrix[arr[0]][arr[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }

        return matrix;
    }
}
