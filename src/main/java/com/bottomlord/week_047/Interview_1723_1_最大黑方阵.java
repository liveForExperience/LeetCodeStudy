package com.bottomlord.week_047;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/5/25 8:26
 */
public class Interview_1723_1_最大黑方阵 {
    public int[] findSquare(int[][] matrix) {
        int max = 0, row = matrix.length, col = matrix[0].length;
        int[] ans = new int[3];
        Arrays.fill(ans, -1);
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == 1) {
                    continue;
                }

                int len = max;
                boolean flag1 = true;
                while (r + len < row && c + len < col) {
                    boolean flag2 = true;
                    for (int i = r; i <= r + len; i++) {
                        if (matrix[i][c] != 0) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (matrix[r][i] != 0) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (matrix[r + len][i] != 0) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    for (int i = r; i <= r + len; i++) {
                        if (matrix[i][c + len] != 0) {
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
                    ans[0] = r;
                    ans[1] = c;
                    ans[2] = max;
                }
            }
        }
        return ans[0] == -1 ? new int[0] : ans;
    }
}
