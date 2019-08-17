package com.bottomlord.week_6;

public class LeetCode_994_2 {
    public int orangesRotting(int[][] grid) {
        boolean flag = true;
        int bad = 2, gr = grid.length, gc = grid[0].length;

        for (; flag; bad++) {
            flag = false;
            for (int i = 0; i < gr; i++) {
                for (int j = 0; j < gc; j++) {
                    if (grid[i][j] == bad) {
                        if (i + 1 < gr && grid[i + 1][j] == 1) {
                            grid[i + 1][j] = bad + 1;
                            flag = true;
                        }

                        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                            grid[i - 1][j] = bad + 1;
                            flag = true;
                        }

                        if (j + 1 < gc && grid[i][j + 1] == 1) {
                            grid[i][j + 1] = bad + 1;
                            flag = true;
                        }

                        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                            grid[i][j - 1] = bad + 1;
                            flag = true;
                        }
                    }
                }
            }
        }

        for (int[] ints : grid) {
            for (int j = 0; j < gc; j++) {
                if (ints[j] == 1) {
                    return -1;
                }
            }
        }

        return bad - 3;
    }
}