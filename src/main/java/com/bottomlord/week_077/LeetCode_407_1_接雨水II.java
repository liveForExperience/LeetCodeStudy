package com.bottomlord.week_077;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/12/29 8:33
 */
public class LeetCode_407_1_接雨水II {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int row = heightMap.length, col = heightMap[0].length;
        boolean[][] visited = new boolean[row][col];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    queue.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        int[] dirs = {1, 0, -1, 0, 1};
        int sum = 0;
        while (!queue.isEmpty()) {
            int[] shortArr = queue.poll();
            int shortR = shortArr[0], shortC = shortArr[1], shortHeight = shortArr[2];

            for (int i = 0; i < 4; i++) {
                int r = shortR + dirs[i], c = shortC + dirs[i + 1];

                if (r < 0 || r >= row || c < 0 || c >= col || visited[r][c]) {
                    continue;
                }

                int height = heightMap[r][c];
                if (height < shortHeight) {
                    sum += shortHeight - height;
                }

                queue.offer(new int[]{r, c, Math.max(height, shortHeight)});
                visited[r][c] = true;
             }
        }

        return sum;
    }
}
