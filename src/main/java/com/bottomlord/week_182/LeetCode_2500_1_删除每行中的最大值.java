package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-07 06:38:32
 */
public class LeetCode_2500_1_删除每行中的最大值 {
    public int deleteGreatestValue(int[][] grid) {
        int r = grid.length, c = grid[0].length, count = 0, ans = 0;
        boolean[][] memo = new boolean[r][c];

        while (count < c) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < r; i++) {
                int rowMax = Integer.MIN_VALUE;
                int maxC = -1;
                for (int j = 0; j < c; j++) {
                    if (memo[i][j]) {
                        continue;
                    }

                    if (grid[i][j] > rowMax) {
                        maxC = j;
                        rowMax = grid[i][j];
                    }
                }

                memo[i][maxC] = true;
                max = Math.max(max, rowMax);
            }

            count++;
            ans += max;
        }
        return ans;
    }
}
