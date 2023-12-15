package com.bottomlord.week_226;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static com.bottomlord.LeetCodeUtils.convertToMatrix;

/**
 * @author chen yue
 * @date 2023-11-10 19:49:16
 */
public class LeetCode_2258_1_逃离火灾 {
    private int[][] grid, matrix, dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
    private int m, n;
    public int maximumMinutes(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.matrix = new int[m][n];
        this.grid = grid;
        for (int[] arr : matrix) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        bfs();

        int head = 0, tail = m * n, ans = -1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (check(mid)) {
                ans = mid;
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return ans >= m * n ? 1000000000 : ans;
    }

    private void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[m][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int t = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int x = arr[0], y = arr[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] != 0 || memo[nx][ny]) {
                        continue;
                    }

                    memo[nx][ny] = true;
                    matrix[nx][ny] = t;
                    queue.offer(new int[]{nx, ny});
                }
            }
            t++;
        }
    }

    private boolean check(int stay) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        memo[0][0] = true;

        int t = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int x = arr[0], y = arr[1];

                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx == m - 1 && ny == n - 1 && matrix[nx][ny] >= t + stay) {
                        return true;
                    }

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 2 || memo[nx][ny] || matrix[nx][ny] <= t + stay) {
                        continue;
                    }

                    memo[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }

            t++;
        }

        return false;
    }

    public static void main(String[] args) {
        LeetCode_2258_1_逃离火灾 t = new LeetCode_2258_1_逃离火灾();
        t.maximumMinutes(convertToMatrix("" +
                "[[0,2,0,0,1]," +
                " [0,2,0,2,2]," +
                " [0,2,0,0,0]," +
                " [0,0,2,2,0]," +
                " [0,0,0,0,0]]"));
    }
}
