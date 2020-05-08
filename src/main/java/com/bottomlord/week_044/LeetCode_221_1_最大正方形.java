package com.bottomlord.week_044;

/**
 * @author ChenYue
 * @date 2020/5/8 8:10
 */
public class LeetCode_221_1_最大正方形 {
    public int maximalSquare(char[][] matrix) {
        int ans = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }

                ans = Math.max(ans, 1);

                int max = Math.min(row - i, col - j);

                for (int k = 1; k < max; k++) {
                    boolean flag = true;
                    if (matrix[i + k][j + k] == '0') {
                        break;
                    }

                    for (int l = 0; l < k; l++) {
                        if (matrix[i + l][j + k] == '0' || matrix[i + k][j + l] == '0') {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        ans = Math.max((1 + k) * (1 + k), ans);
                    } else {
                        break;
                    }
                }
            }
        }

        return ans;
    }
}
