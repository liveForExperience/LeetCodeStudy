package com.bottomlord.week_067;

/**
 * @author ChenYue
 * @date 2020/10/21 18:04
 */
public class LeetCode_308_1_二维区域和检索_可变 {
    class NumMatrix {
        private int[][] trees;
        int r, c;
        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            r = matrix.length;
            c = matrix[0].length;
            trees = new int[r][2 * c];

            for (int i = 0; i < r; i++) {
                for (int j = 0, k = c; k < 2 * c; j++, k++) {
                    trees[i][k] = matrix[i][j];
                }

                for (int j = c - 1; j > 0; j--) {
                    trees[i][j] = matrix[i][j * 2] + matrix[i][j * 2 + 1];
                }
            }
        }

        public void update(int row, int col, int val) {
            trees[row][col] = val;
            col += c;

            while (col > 0) {
                int l = col % 2 == 0 ? col : col - 1,
                    r = col % 2 == 0 ? col + 1 : col;

                col /= 2;
                trees[row][col] = trees[row][l] + trees[row][r];
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int r = row1; r <= row2; r++) {
                int left = col1 + c, right = col2 + c;
                while (left <= right) {
                    if (left % 2 == 1) {
                        sum += trees[r][left++];
                    }

                    if (right % 2 == 0) {
                        sum += trees[r][right--];
                    }

                    left /= 2;
                    right /= 2;
                }
            }

            return sum;
        }
    }
}
