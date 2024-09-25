package com.bottomlord.week_047;

/**
 * @author ChenYue
 * @date 2020/5/25 8:47
 */
public class LeetCode_1139_1_最大的以1为边界的正方形 {
    public int largest1BorderedSquare(int[][] grid) {
        int max = 0, row = grid.length, col = grid[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }

                int len = max;
                boolean flag1 = true;
                while (r + len < row && c + len < col) {
                    boolean flag2 = true;
                    for (int i = r; i <= r + len; i++) {
                        if (grid[i][c] != 1) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (grid[r][i] != 1) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (grid[r + len][i] != 1) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    for (int i = r; i <= r + len; i++) {
                        if (grid[i][c + len] != 1) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    len++;
                    max = len;
                }
            }
        }
        return max * max;
    }
}
