package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/13 19:59
 */
public class Interview_04_1_二维数组中的查找 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        for (int[] row : matrix) {
            for (int num : row) {
                if (num == target) {
                    return true;
                }

                if (num > target) {
                    break;
                }
            }
        }

        return false;
    }
}
