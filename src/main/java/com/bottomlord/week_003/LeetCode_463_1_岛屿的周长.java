package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/23 13:47
 */
public class LeetCode_463_1_岛屿的周长 {
    public int islandPerimeter(int[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i - 1 < 0 || grid[i - 1][j] == 0) {
                        count++;
                    }

                    if (i + 1 > grid.length - 1 || grid[i + 1][j] == 0) {
                        count++;
                    }

                    if (j - 1 < 0 || grid[i][j - 1] == 0) {
                        count++;
                    }

                    if (j + 1 > grid[i].length - 1 || grid[i][j + 1] == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
