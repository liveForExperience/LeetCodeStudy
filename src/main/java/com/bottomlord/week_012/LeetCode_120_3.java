package com.bottomlord.week_012;

import java.util.List;

public class LeetCode_120_3 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[][] dp = new int[row][row];

        for (int level = row - 1; level >= 0; level--) {
            for (int index = 0; index < triangle.get(level).size(); index++) {
                if (level == row - 1) {
                    dp[level][index] = triangle.get(level).get(index);
                } else {
                    dp[level][index] = Math.min(dp[level + 1][ index], dp[level + 1][index + 1]) + triangle.get(level).get(index);
                }
            }
        }

        return dp[0][0];
    }
}