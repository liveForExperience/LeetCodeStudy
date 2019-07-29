package com.bottomlord.week_4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/29 13:35
 */
public class LeetCode_1030_2 {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int max = Math.max(Math.max(r0 + c0, R - r0 + c0), Math.max(C - c0 + R - r0, C - c0 + r0));
        List<int[]> ans = new ArrayList<>();
        ans.add(new int[]{r0, c0});

        int x, y;
        for (int i = 1; i < max; i++) {
            for (int j = 0; j <= i; j++) {
                x = r0 - i + j; y = c0 - j;
                if (x >= 0 && y >= 0) {
                    ans.add(new int[]{x, y});
                }

                x = r0 + j; y = c0 - i + j;
                if (x < R && y >= 0) {
                    ans.add(new int[]{x, y});
                }

                x = r0 + i - j; y = c0 + j;
                if (x < R && y < C) {
                    ans.add(new int[]{x, y});
                }

                x = r0 - j; y = c0 + i - j;
                if(x >= 0 && y < C) {
                    ans.add(new int[]{x, y});
                }
            }
        }

        return ans.toArray(new int[0][0]);
    }
}