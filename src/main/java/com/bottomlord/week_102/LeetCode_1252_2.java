package com.bottomlord.week_102;

/**
 * @author ChenYue
 * @date 2021/6/23 8:53
 */
public class LeetCode_1252_2 {
    public int oddCells(int m, int n, int[][] indices) {
        int[] row = new int[m], col = new int[n];
        for (int[] indic : indices) {
            row[indic[0]] ^= 1;
            col[indic[1]] ^= 1;
        }

        int rowNum = 0, colNum = 0;
        for (int num : row) {
            if (num == 1) {
                rowNum++;
            }
        }

        for (int num : col) {
            if (num == 1) {
                colNum++;
            }
        }

        return rowNum * n + colNum * m - 2 * rowNum * colNum;
    }
}
