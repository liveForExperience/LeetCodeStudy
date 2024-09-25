package com.bottomlord.week_012;

public class LeetCode_64_4 {
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length, colLen = grid[0].length;
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                if (i == rowLen - 1 && j != colLen - 1) {
                    grid[i][j] += grid[i][j + 1];
                } else if (i != rowLen - 1 && j == colLen - 1) {
                    grid[i][j] += grid[i + 1][j];
                } else if (i != rowLen - 1 && j != colLen - 1){
                    grid[i][j] += Math.min(grid[i + 1][j], grid[i][j + 1]);
                }
            }
        }

        return grid[0][0];
    }
}
