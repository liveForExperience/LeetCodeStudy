package com.bottomlord.week_060;

/**
 * @author ChenYue
 * @date 2020/8/25 8:46
 */
public class LeetCode_240_2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }

        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }

        int r = 0, c = col - 1;
        while (r < row && c >= 0) {
            int num = matrix[r][c];
            if (target == num) {
                return true;
            } else if (target > num) {
                r++;
            } else {
                c--;
            }
        }

        return false;
    }
}
