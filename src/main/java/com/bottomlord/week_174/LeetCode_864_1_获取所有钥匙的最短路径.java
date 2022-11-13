package com.bottomlord.week_174;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-11-13 10:54:10
 */
public class LeetCode_864_1_获取所有钥匙的最短路径 {
    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestPathAllKeys(String[] grid) {
        int row = grid.length, col = grid[0].length(), num = 0, sr = -1, sc = -1;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            char[] cs = grid[i].toCharArray();
            for (int j = 0; j < cs.length; j++) {
                char c = cs[j];
                if (Character.isLowerCase(c)) {
                    num++;
                }

                if (c == '@') {
                    sr = i;
                    sc = j;
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        int mask = (1 << num) - 1;
        int[][][] matrix = new int[row][col][mask + 1];
        for (int[][] r : matrix) {
            for (int[] c : r) {
                Arrays.fill(c, -1);
            }
        }
        matrix[sr][sc][0] = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int r = arr[0], c = arr[1], s = arr[2];

                for (int[] dir : dirs) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr < 0 || nr >= row || nc < 0 || nc >= col) {
                        continue;
                    }

                    char ch = grid[nr].charAt(nc);
                    if (ch == '#') {
                        continue;
                    }

                    int ns = s;
                    if (Character.isLowerCase(ch)) {
                        ns |= (1 << (ch - 'a'));
                    }

                    if (matrix[nr][nc][ns] != -1) {
                        continue;
                    }

                    if (Character.isUpperCase(ch)) {
                        if ((s & (1 << (ch - 'A'))) == 0) {
                            continue;
                        }
                    }

                    matrix[nr][nc][ns] = matrix[r][c][s] + 1;
                    if (ns == mask) {
                        return matrix[nr][nc][ns];
                    }

                    queue.offer(new int[]{nr, nc, ns});
                }
            }
        }

        return -1;
    }
}
