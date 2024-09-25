package com.bottomlord.week_175;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-11-20 16:36:38
 */
public class LeetCode_799_1_香槟塔 {
    public double champagneTower(int p, int x, int y) {
        double[][] dp = new double[x + 2][x + 2];
        dp[0][0] = p;

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] <= 1) {
                    continue;
                }

                dp[i + 1][j] += (dp[i][j] - 1) / 2;
                dp[i + 1][j + 1] += (dp[i][j] - 1) / 2;
            }
        }

        return Math.min(dp[x][y], 1);
    }
}
