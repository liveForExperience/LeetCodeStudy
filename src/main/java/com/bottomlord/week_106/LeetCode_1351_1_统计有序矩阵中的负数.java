package com.bottomlord.week_106;

/**
 * @author ChenYue
 * @date 2021/7/20 8:53
 */
public class LeetCode_1351_1_统计有序矩阵中的负数 {
    public int countNegatives(int[][] grid) {
        int col = grid[0].length;
        int count = 0;
        for (int[] rows : grid) {
            for (int c = col - 1; c >= 0; c--) {
                if (rows[c] >= 0) {
                    break;
                }
                count++;
            }
        }
        return count;
    }
}
