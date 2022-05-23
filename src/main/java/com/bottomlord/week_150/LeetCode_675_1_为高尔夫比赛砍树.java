package com.bottomlord.week_150;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-05-23 16:42:16
 */
public class LeetCode_675_1_为高尔夫比赛砍树 {
    private int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size(), n = forest.get(0).size();
        int[][] matrix = new int[50][50];
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = forest.get(i).get(j);
                if (matrix[i][j] > 1) {
                    list.add(new int[]{matrix[i][j], i, j});
                }
            }
        }

        if (list.get(0)[0] == 0) {
            return -1;
        }

        list.sort(Comparator.comparingInt(x -> x[0]));

        int ans = 0, x = 0, y = 0;
        for (int[] arr : list) {
            int nx = arr[1], ny = arr[2];

            int path = bfs(matrix, x, nx, y, ny, m, n);
            if (path == -1) {
                return -1;
            }

            ans += path;

            x = nx;
            y = ny;
        }

        return ans;
    }

    private int bfs(int[][] matrix, int x, int nx, int y, int ny, int r, int c) {
        if (x == nx && y == ny) {
            return 0;
        }

        int ans = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        boolean[][] memo = new boolean[r][c];
        memo[x][y] = true;

        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int a = arr[0], b = arr[1];

                for (int[] dir : dirs) {
                    int na = a + dir[0], nb = b + dir[1];
                    if (na < 0 || na >= r || nb < 0 || nb >= c || matrix[na][nb] == 0 || memo[na][nb]) {
                        continue;
                    }

                    if (na == nx && nb == ny) {
                        return ans + 1;
                    }

                    queue.offer(new int[]{na, nb});
                    memo[na][nb] = true;
                }
            }

            ans++;
        }

        return -1;
    }
}
