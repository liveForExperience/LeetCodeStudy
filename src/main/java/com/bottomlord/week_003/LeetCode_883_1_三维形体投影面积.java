package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/24 12:40
 */
public class LeetCode_883_1_三维形体投影面积 {
    public int projectionArea(int[][] grid) {
        int row = 0;
        int col = 0;
        int floor = 0;

        int[] colArr = new int[grid.length];

        for (int i = 0; i < grid.length; i++) {
            int rowMax = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    floor++;
                }

                rowMax = Math.max(grid[i][j], rowMax);
                colArr[j] = Math.max(colArr[j], grid[i][j]);
            }
            row += rowMax;
        }

        for (int num : colArr) {
            col += num;
        }

        return row + col + floor;
    }
}
