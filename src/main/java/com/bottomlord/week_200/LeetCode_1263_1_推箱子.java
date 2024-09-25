package com.bottomlord.week_200;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2023-05-08 22:42:29
 */
public class LeetCode_1263_1_推箱子 {
    private int r, c;
    private int[][] memo;
    private char[][] grid;

    public int minPushBox(char[][] grid) {
        this.r = grid.length;
        this.c = grid[0].length;
        this.memo = new int[c * r][c * r];
        this.grid = grid;

        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx * c + sy, bx * c + by});
        int[] dirs = new int[]{0, -1, 0, 1, 0};
        for (int[] arr : memo) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        memo[sx * c + sy][bx * c + by] = 0;

        while (!queue.isEmpty()) {
            Queue<int[]> next = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int s1 = arr[0], b1 = arr[1],
                        sx1 = s1 / c, sy1 = s1 % c,
                        bx1 = b1 / c, by1 = b1 % c;

                if (grid[bx1][by1] == 'T') {
                    return memo[s1][b1];
                }

                for (int i = 0; i < 4; i++) {
                    int sx2 = sx1 + dirs[i], sy2 = sy1 + dirs[i + 1], s2 = sx2 * c + sy2;

                    if (!ok(sx2, sy2)) {
                        continue;
                    }

                    if (sx2 == bx1 && sy2 == by1) {
                        int bx2 = bx1 + dirs[i], by2 = by1 + dirs[i + 1], b2 = bx2 * c + by2;
                        if (!ok(bx2, by2) || memo[s2][b2] <= memo[s1][b1] + 1) {
                            continue;
                        }

                        memo[s2][b2] = memo[s1][b1] + 1;
                        next.offer(new int[]{s2, b2});
                    } else {
                        if (memo[s2][b1] <= memo[s1][b1]) {
                            continue;
                        }

                        memo[s2][b1] = memo[s1][b1];
                        queue.offer(new int[]{s2, b1});
                    }
                }
            }

            queue = next;
        }

        return -1;
    }

    private boolean ok(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c && grid[x][y] != '#';
    }
}
