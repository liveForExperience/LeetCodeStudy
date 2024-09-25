package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/13 20:52
 */
public class Interview_04_3 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        int x = 0, y = matrix[0].length - 1;
        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] > target) {
                y--;
            } else if (matrix[x][y] < target) {
                x++;
            } else {
                return true;
            }
        }

        return false;
    }
}
