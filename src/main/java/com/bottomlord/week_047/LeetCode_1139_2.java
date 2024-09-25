package com.bottomlord.week_047;

/**
 * @author ChenYue
 * @date 2020/5/26 11:31
 */
public class LeetCode_1139_2 {
    public int largest1BorderedSquare(int[][] grid) {
        int row = grid.length, col = grid[0].length, ans = 0;
        int[][] r = new int[row][col],
                c = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }

                r[i][j] = 1;
                c[i][j] = 1;

                if (i > 0) {
                    r[i][j] += r[i - 1][j];
                }

                if (j > 0) {
                    c[i][j] += c[i][j - 1];
                }

                int max = Math.min(r[i][j], c[i][j]);
                for (int k = 1; k <= max; k++) {
                    if (k > ans && r[i - k + 1][j] >= k && c[i][j - k + 1] >= k) {
                        ans = k;
                    }
                }
            }
        }

        return ans;
    }
}
