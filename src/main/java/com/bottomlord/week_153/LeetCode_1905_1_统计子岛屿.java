package com.bottomlord.week_153;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-06-01 15:39:14
 */
public class LeetCode_1905_1_统计子岛屿 {
    private int[][] grid1, grid2;
    private int r, c;
    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        this.grid1 = grid1;
        this.grid2 = grid2;
        this.r = grid1.length;
        this.c = grid1[0].length;
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid2[i][j] == 1) {
                    count += bfs(i, j) ? 1 : 0;
                }
            }
        }

        return count;
    }

    private boolean bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        grid2[x][y] = 0;
        boolean flag = grid1[x][y] == 1;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (arr == null) {
                continue;
            }

            int a = arr[0], b = arr[1];

            for (int[] dir : dirs) {
                int na = a + dir[0], nb = b + dir[1];
                if (na >= 0 && na < r && nb >= 0 && nb < c && grid2[na][nb] == 1) {
                    queue.offer(new int[]{na, nb});
                    grid2[na][nb] = 0;

                    if (grid1[na][nb] != 1) {
                        flag = false;
                    }

                }
            }
        }

        return flag;
    }
}
