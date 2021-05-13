package com.bottomlord.week_096;

/**
 * @author ChenYue
 * @date 2021/5/13 8:11
 */
public class LeetCode_1269_1_停在原地的方案数 {
    private int mod = 1000000007;
    public int numWays(int steps, int arrLen) {
        return dfs(steps, arrLen, 0, 0);
    }

    private int dfs(int steps, int arrLen, int index, int step) {
        if (index < 0 || index >= arrLen) {
            return 0;
        }

        if (step == steps) {
            return index == 0 ? 1 : 0;
        }

        if (index > steps - step) {
            return 0;
        }

        int count = 0;
        for (int i = -1; i < 2; i++) {
            count += dfs(steps, arrLen, index + i, step + 1);
            count %= mod;
        }
        return count % mod;
    }
}
