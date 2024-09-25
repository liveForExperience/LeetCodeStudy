package com.bottomlord.week_009;

public class LeetCode_807_1_保持城市天际线 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int count = 0;
        int[] rowMaxArr = new int[grid.length];
        int[] colMaxArr = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int rowMax = 0;
            int colMax = 0;
            for (int j = 0; j < grid[i].length; j++) {
                rowMax = Math.max(rowMax, grid[i][j]);
                colMax = Math.max(colMax, grid[j][i]);
            }
            rowMaxArr[i] = rowMax;
            colMaxArr[i] = colMax;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                count += Math.min(rowMaxArr[i], colMaxArr[j]) - grid[i][j];
            }
        }

        return count;
    }
}
