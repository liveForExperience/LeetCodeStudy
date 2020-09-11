package com.bottomlord.week_062;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/9/11 8:44
 */
public class LeetCode_265_2 {
    public int minCostII(int[][] costs) {
        int row = costs.length;
        if (row == 0) {
            return 0;
        }

        int col = costs[0].length;
        if (col == 0) {
            return 0;
        }

        int one = Integer.MAX_VALUE, oneIndex = 0,
                two = Integer.MAX_VALUE,
                ans = Integer.MAX_VALUE;

        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(i == 0) {
                    dp[i][j] = costs[i][j];
                    if(row == 1) {
                        ans = Math.min(ans, dp[i][j]);
                    }
                    continue;
                }

                if (j != oneIndex) {
                    dp[i][j] = Math.min(dp[i][j], one + costs[i][j]);
                } else {
                    dp[i][j] = Math.min(dp[i][j], two + costs[i][j]);
                }

                if (i == row - 1) {
                    ans = Math.min(ans, dp[i][j]);
                }
            }

            one = Integer.MAX_VALUE;
            oneIndex = 0;
            two = Integer.MAX_VALUE;

            for (int j = 0; j < col; j++) {
                if (dp[i][j] < one) {
                    one = dp[i][j];
                    oneIndex = j;
                }
            }

            for (int j = 0; j < col; j++) {
                if (j != oneIndex && dp[i][j] < two) {
                    two = dp[i][j];
                }
            }
        }

        return ans;
    }
}