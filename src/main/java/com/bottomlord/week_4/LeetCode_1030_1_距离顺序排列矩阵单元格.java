package com.bottomlord.week_4;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/29 9:06
 */
public class LeetCode_1030_1_距离顺序排列矩阵单元格 {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ans = new int[R * C][2];
        int index = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans[index][0] = i;
                ans[index][1] = j;
                index++;
            }
        }

        Arrays.sort(ans, (int[] x, int[] y) -> Math.abs(x[0] - r0) + Math.abs(x[1] - c0) - Math.abs(y[0] - r0) - Math.abs(y[1] - c0));
        return ans;
    }
}
