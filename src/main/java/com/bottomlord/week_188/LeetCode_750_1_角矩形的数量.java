package com.bottomlord.week_188;

import java.util.Map;

/**
 * @author chen yue
 * @date 2023-02-18 10:51:13
 */
public class LeetCode_750_1_角矩形的数量 {
    public int countCornerRectangles(int[][] grid) {
        int ans = 0;
        int r = grid.length, c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }

                for (int i1 = 1; i1 < r - i; i1++) {
                    for (int i2 = 1; i2 < c - j; i2++) {
                        if (grid[i + i1][j + i2] == 1 &&
                                grid[i][j + i2] == 1 &&
                                grid[i + i1][j] == 1) {
                            ans++;
                        }
                    }
                }
            }
        }

        return ans;
    }
}
