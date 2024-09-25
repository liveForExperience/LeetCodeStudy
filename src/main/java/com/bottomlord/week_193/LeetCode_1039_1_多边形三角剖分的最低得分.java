package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-04-02 12:01:35
 */
public class LeetCode_1039_1_多边形三角剖分的最低得分 {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        return dfs(0, n - 1, values, new Integer[n][n]);
    }

    private int dfs(int i, int j, int[] values, Integer[][] memo) {
        if (i + 1 == j) {
            return 0;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int ans = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            ans = Math.min(dfs(i, k, values, memo) + dfs(k, j, values, memo) + values[i] * values[j] * values[k], ans);
        }

        return memo[i][j] = ans;
    }
}
