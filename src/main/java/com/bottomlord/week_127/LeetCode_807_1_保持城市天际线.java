package com.bottomlord.week_127;

import java.util.Arrays;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-12-13 08:54:37
 */
public class LeetCode_807_1_保持城市天际线 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] rowMax = new int[row], colMax = new int[col];

        for (int i = 0; i < row; i++) {
            rowMax[i] = Arrays.stream(grid[i]).max().getAsInt();
        }

        for (int i = 0; i < col; i++) {
            int max = 0;
            for (int j = 0; j < row; j++) {
                max = Math.max(max, grid[j][i]);
            }
            colMax[i] = max;
        }

        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sum += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }

        return sum;
    }
}
