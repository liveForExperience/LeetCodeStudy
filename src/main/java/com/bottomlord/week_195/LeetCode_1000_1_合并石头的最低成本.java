package com.bottomlord.week_195;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-04-04 09:12:40
 */
public class LeetCode_1000_1_合并石头的最低成本 {

    private int[][][] memo;
    private int[] sums;
    private int k;

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }

        this.memo = new int[n][n][k + 1];
        this.sums = new int[n + 1];
        this.k = k;
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + stones[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return dfs(0, n - 1, 1);
    }

    private int dfs(int i, int j, int p) {
        if (memo[i][j][p] != -1) {
            return memo[i][j][p];
        }

        if (p == 1) {
            return memo[i][j][p] = i == j ? 0 : dfs(i, j, k) + sums[j + 1] - sums[i];
        }

        int ans = Integer.MAX_VALUE;
        for (int index = i; index < j; index += k - 1) {
            ans = Math.min(ans, dfs(i, index, 1) + dfs(index + 1, j, p - 1));
        }
        return memo[i][j][p] = ans;
    }
}
