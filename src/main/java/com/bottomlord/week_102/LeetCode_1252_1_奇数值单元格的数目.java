package com.bottomlord.week_102;

/**
 * @author ChenYue
 * @date 2021/6/23 8:38
 */
public class LeetCode_1252_1_奇数值单元格的数目 {
    public int oddCells(int m, int n, int[][] indices) {
        int[][] matrix = new int[m][n];
        for (int[] indic : indices) {
            for (int i = 0; i < m; i++) {
                matrix[i][indic[1]]++;
            }

            for (int i = 0; i < n; i++) {
                matrix[indic[0]][i]++;
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] % 2 == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
