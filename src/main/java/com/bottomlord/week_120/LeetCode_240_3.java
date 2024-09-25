package com.bottomlord.week_120;

/**
 * @author chen yue
 * @date 2021-10-25 08:57:40
 */
public class LeetCode_240_3 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length,
            r = 0, c = col - 1;

        while (r < row && c >= 0) {
            int num = matrix[r][c];
            if (num == target) {
                return true;
            } else if (num > target) {
                c--;
            } else {
                r++;
            }
        }

        return false;
    }
}
