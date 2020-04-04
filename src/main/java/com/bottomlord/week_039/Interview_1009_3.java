package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/4 23:17
 */
public class Interview_1009_3 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length, col = matrix[0].length, ri = 0, ci = col - 1;

        while (ri < row && ci >= 0) {
            if (matrix[ri][ci] == target) {
                return true;
            }

            if (matrix[ri][ci] > target) {
                ci--;
            } else {
                ri++;
            }
        }

        return false;
    }
}