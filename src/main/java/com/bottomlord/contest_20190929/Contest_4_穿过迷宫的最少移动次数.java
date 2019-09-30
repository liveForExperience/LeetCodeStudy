package com.bottomlord.contest_20190929;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Contest_4_穿过迷宫的最少移动次数 {
    public int minimumMoves(int[][] grid) {
        int len = grid.length;
        int[][][] dp = new int[len][len][2];

        Queue<int[]> queue = new ArrayDeque<>();

        dp[0][0][0] = 0;
        queue.offer(new int[]{0,0,0});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1], way = arr[2];

            if (x == len - 1 && y == len - 2 && way == 0) {
                break;
            }

            int count = dp[x][y][way];

            if (way == 0) {
                if (y != len - 2 && grid[x][y + 2] != 1) {
                    move(queue, dp, x, y + 1, way, count);
                }

                if (x != len - 1 && grid[x + 1][y] != 1 && grid[x + 1][y + 1] != 1) {
                    move(queue, dp, x + 1, y, way, count);
                }

                if (x != len - 1 && grid[x + 1][y] != 1 && grid[x + 1][y + 1] != 1) {
                    move(queue, dp, x, y, 1 - way, count);
                }
            } else {
                if (y != len - 1 && grid[x][y + 1] != 1 && grid[x + 1][y + 1] != 1) {
                    move(queue, dp, x, y + 1, way, count);
                }

                if (x != len - 2 && grid[x + 2][y] != 1) {
                    move(queue, dp, x + 1, y, way, count);
                }

                if (y != len - 1 && grid[x][y + 1] != 1 && grid[x + 1][y + 1] != 1) {
                    move(queue, dp, x, y, 1 - way, count);
                }
            }
        }

        return dp[len - 1][len - 2][0] == 0 ? -1 : dp[len - 1][len - 2][0];
    }

    private void move(Queue<int[]> queue, int[][][] dp, int x, int y, int way, int count) {
        if (dp[x][y][way] == 0) {
            queue.offer(new int[]{x,y,way});
            dp[x][y][way] = count + 1;
        }
    }
}
