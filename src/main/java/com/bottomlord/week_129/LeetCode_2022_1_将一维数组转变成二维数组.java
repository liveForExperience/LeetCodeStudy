package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2022-01-01 10:08:09
 */
public class LeetCode_2022_1_将一维数组转变成二维数组 {
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[0][0];
        }

        int[][] arr = new int[m][n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = original[index++];
            }
        }

        return arr;
    }
}
