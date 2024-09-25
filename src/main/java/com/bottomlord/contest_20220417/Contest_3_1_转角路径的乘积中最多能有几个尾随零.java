package com.bottomlord.contest_20220417;

/**
 * @author chen yue
 * @date 2022-04-17 11:54:07
 */
public class Contest_3_1_转角路径的乘积中最多能有几个尾随零 {
    public int maxTrailingZeros(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] rs2 = new int[row + 1][col], rs5 = new int[row + 1][col],
                cs2 = new int[row][col + 1], cs5 = new int[row][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int num = grid[i - 1][j - 1], c2 = count(num, 2), c5 = count(num, 5);
                rs2[i][j - 1] = rs2[i - 1][j - 1] + c2;
                rs5[i][j - 1] = rs5[i - 1][j - 1] + c5;
                cs2[i - 1][j] = cs2[i - 1][j - 1] + c2;
                cs5[i - 1][j] = cs5[i - 1][j - 1] + c5;
            }
        }

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int u2 = rs2[i + 1][j], u5 = rs5[i + 1][j];
                int l2 = cs2[i][j], l5 = cs5[i][j];
                int d2 = rs2[row][j] - rs2[i][j], d5 = rs5[row][j] - rs5[i][j];
                int r2 = cs2[i][col] - cs2[i][j + 1], r5 = cs5[i][col] - cs5[i][j + 1];

                max = Math.max(max, Math.min(u2 + l2, u5 + l5));
                max = Math.max(max, Math.min(u2 + r2, u5 + r5));
                max = Math.max(max, Math.min(d2 + l2, d5 + l5));
                max = Math.max(max, Math.min(d2 + r2, d5 + r5));
            }
        }
        return max;
    }

    private int count(int num, int target) {
        int count = 0;
        while (num > 0) {
            if (num % target != 0) {
                break;
            }
            num /= target;
            count++;
        }

        return count;
    }
}
