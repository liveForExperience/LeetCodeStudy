package com.bottomlord.week_124;

/**
 * @author chen yue
 * @date 2021-11-28 21:45:51
 */
public class LeetCode_1886_1_判断矩阵经轮转后是否一致 {
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            if (isSame(mat, target)) {
                return true;
            }
            mat = rotation(mat);
        }

        return false;
    }

    private boolean isSame(int[][] x, int[][] y) {
        int len = x[0].length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (x[i][j] != y[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] rotation(int[][] mat) {
        int len = mat[0].length;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = mat[j][len - 1 - i];
            }
        }
        return matrix;
    }
}
