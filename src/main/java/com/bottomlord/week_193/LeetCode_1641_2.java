package com.bottomlord.week_193;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-03-29 09:11:21
 */
public class LeetCode_1641_2 {
    public int countVowelStrings(int n) {
        int[][] dp = new int[n][5];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += dp[n - 1][i];
        }

        return sum;
    }
}
