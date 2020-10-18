package com.bottomlord.week_066;

/**
 * @author ChenYue
 * @date 2020/10/18 13:44
 */
public class LeetCode_304_1_二维区域和检索_矩阵不可变 {
    class NumMatrix {
        private int[][] matrix;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }

            this.matrix = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                int sum = 0;
                for (int j = 0; j < matrix[0].length; j++) {
                    this.matrix[i][j] = (sum += matrix[i][j]);
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                if (col1 == 0) {
                    sum += matrix[i][col2];
                } else {
                    sum += matrix[i][col2] - matrix[i][col1 - 1];
                }
            }
            return sum;
        }
    }
}
