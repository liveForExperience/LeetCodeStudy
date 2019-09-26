package com.bottomlord.contest_20190924;

public class Contest_4_覆盖 {
    private int count = 0;
    public int domino(int n, int m, int[][] broken) {
        boolean[][] grid = new boolean[n][m];
        for (int[] b : broken) {
            grid[b[0]][b[1]] = true;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!grid[i][j]) {
                    if (i != grid.length - 1) {
                        if (!grid[i + 1][j]) {
                            grid[i][j] = true;
                            grid[i + 1][j] = true;
                            count++;
                            continue;
                        }
                    }

                    if (j != grid[i].length - 1) {
                        if (!grid[i][j + 1]) {
                            grid[i][j] = true;
                            grid[i][j + 1] = true;
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}
