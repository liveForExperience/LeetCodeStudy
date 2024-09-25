package com.bottomlord.week_175;

/**
 * @author chen yue
 * @date 2022-11-21 22:07:29
 */
public class LeetCode_808_2 {
    public double soupServings(int n) {
        n = Math.min(200, (int) Math.ceil(n / 25.0));
        double[][] memo = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                memo[i][j] = -1;
            }
        }
        return dfs(n, n, memo);
    }

    private double dfs(int a, int b, double[][] memo) {
        if (a == 0 && b > 0) {
            return 1;
        }

        if (a == 0 && b == 0) {
            return 0.5;
        }

        if (a > 0 && b == 0) {
            return 0;
        }

        if (memo[a][b] != -1) {
            return memo[a][b];
        }

        memo[a][b] = 0.25 * (
                dfs(Math.max(0, a - 4), b, memo) +
                        dfs(Math.max(0, a - 3), Math.max(b - 1, 0), memo) +
                        dfs(Math.max(0, a - 2), Math.max(b - 2, 0), memo) +
                        dfs(Math.max(0, a - 1), Math.max(b - 3, 0), memo)
        );

        return memo[a][b];
    }
}